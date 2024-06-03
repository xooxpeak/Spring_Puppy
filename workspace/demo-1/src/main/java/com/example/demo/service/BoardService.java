package com.example.demo.service;

import com.example.demo.config.SecurityUtil;
import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.BoardMapper;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.LikeListRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BoardService {

	@Autowired
	private  BoardRepository boardRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private LikeListRepository likeListRepository;

	// 게시글 작성
	public BoardDTO createBoard(BoardDTO boardDTO) {

		// 현재 사용자의 ID를 가져옴
		String currentUserId = SecurityUtil.getCurrentUserId();

		// 로그인된 사용자가 없는 경우 예외 처리
		if (currentUserId == null || "anonymousUser".equals(currentUserId)) {
			throw new RuntimeException("로그인한 사용자가 없습니다.");
		}

		// 현재 사용자의 ID로 회원 정보를 가져옴
//		UserEntity userEntity = userRepository.findByUserId(currentUserId);
//				.orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
		Optional<UserEntity> userOptional = Optional.ofNullable(userRepository.findByUserId(currentUserId));

		// 사용자가 존재하지 않는 경우 예외 처리
		UserEntity userEntity = userOptional.orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));

		// 새로운 게시글 생성: 클라이언트로부터 받은 DTO를 엔티티로 변환하고 필요한 필드 설정
		BoardEntity boardEntity = BoardEntity.builder()
				.userId(userEntity.getId())  // 현재 사용자의 ID로 설정
				.user(userEntity)  // UserEntity 객체를 설정
				.title(boardDTO.getTitle())
				.content(boardDTO.getContent())
				.boardDate(Date.valueOf(LocalDate.now())) // 현재 날짜 설정
				.views(0) // 초기 조회수 설정
				.userLike(0) // 초기 좋아요 수 설정
				.build();

		// 게시글 저장
		boardRepository.save(boardEntity);

		// 클라이언트로 응답을 보내기 위해 entity를 DTO로 변환
		return BoardMapper.instance.boardToDTO(boardEntity);
	}

	
	// 게시글 목록 조회
	public List<BoardDTO> board() {
		List<BoardEntity> board = boardRepository.findAll();  // 모든 게시글을 DB에서 조회
		return board.stream()  // 조회된 각각의 BoardEntity 객체를 스트림으로 변환
//				.map(BoardMapper.instance::boardToDTO)  // 각각의 BoardEntity 객체를 BoardDTO 객체로 변환. 의존성 주입 x
				.map(boardMapper::boardToDTO)  // 의존성 주입하여 사용
				.collect(Collectors.toList());  // 리스트로 수집하여 반환
	}


	// 게시글 상세 조회
//	public BoardDTO boardDetail(Long id) {
//		Optional<BoardEntity> board = boardRepository.findById(id);
//		if (board.isPresent()) {
//			return BoardMapper.instance.boardToDTO(board.get());
//		} else {
//			throw new RuntimeException("게시글을 찾을 수 없습니다.");
//		}
//	public BoardDTO boardDetail(Long id) {
//		return boardRepository.findById(id)
////				.map(boardMapper::boardToDTO)
//				.map(boardEntity -> {
//					// 현재 인증된 사용자의 ID를 가져옴
//					String currentUserId = SecurityUtil.getCurrentUserId();
//					// 로그인된 사용자가 없는 경우 예외 처리
//					if (currentUserId == null || "anonymousUser".equals(currentUserId)) {
//						throw new RuntimeException("로그인한 사용자가 없습니다.");
//					}
//					// 게시글 작성자의 ID를 가져옴
//					Long authorId = boardEntity.getUserId();
//					// 현재 사용자와 게시글 작성자의 ID가 일치하는지 확인
//					boolean isAuthor = currentUserId.equals(authorId.toString());
//					// 게시글 상세 정보를 DTO로 매핑하여 반환
//					return boardMapper.boardToDTOWithAuthor(boardEntity, isAuthor);
//				})
//				.orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
//	}

	public Map<String, Object> boardDetail(Long id) {
		BoardEntity boardEntity = boardRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

		String currentUserId = SecurityUtil.getCurrentUserId();
		if (currentUserId == null || "anonymousUser".equals(currentUserId)) {
			throw new RuntimeException("로그인한 사용자가 없습니다.");
		}

		// TODO: currentUserId 사용할 수 없는지 재확인하기
		UserEntity currentUser = userRepository.findByUserId(currentUserId);


		// 게시글 작성자 확인
//		Long authorId = boardEntity.getUserId();
		String authorId = boardEntity.getUser().getUserId();   // id(pk)가 아닌 userId로 하여 currentUserId와 맞춰줌
		boolean isAuthor = currentUserId.equals(authorId);

		// 좋아요 여부 확인
		boolean isLiked = likeListRepository.findByUserAndBoard(currentUser, boardEntity).isPresent();
		int likeCount = likeListRepository.countByBoard(boardEntity);

		BoardDTO boardDTO = BoardDTO.of(boardEntity, isAuthor);

		Map<String, Object> response = new HashMap<>();
		response.put("board", boardDTO);
		response.put("isAuthor", isAuthor);
		response.put("isLiked", isLiked);
		response.put("likeCount", likeCount);

		return response;
	}


	// 게시글 삭제
	public void deleteBoard(Long id) {
		Optional<BoardEntity> boardEntity = boardRepository.findById(id);
		boardRepository.delete(boardEntity.get());
	}


	// 게시글 수정
	public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
		BoardEntity board = boardRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid board ID: " + id));

		// 제목과 내용을 업데이트
		board.setTitle(boardDTO.getTitle());
		board.setContent(boardDTO.getContent());

		boardRepository.save(board);

		return boardMapper.boardToDTO(board);
	}


	// 조회수
	public int updateView(Long id) {
		return boardRepository.updateView(id);
	}

	// 좋아요 토글
//	public Map<String, Object> toggleLike(Long boardId) {
//		String currentUserId = SecurityUtil.getCurrentUserId();
//		if (currentUserId == null || "anonymousUser".equals(currentUserId)) {
//			throw new RuntimeException("로그인한 사용자가 없습니다.");
//		}
//
//		UserEntity currentUser = userRepository.findByUserId(currentUserId)
//				.orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));
//
//		BoardEntity boardEntity = boardRepository.findById(boardId)
//				.orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
//
//		Optional<LikeListEntity> existingLike = likeListRepository.findByUserAndBoard(currentUser, boardEntity);
//		boolean isLiked;
//		if (existingLike.isPresent()) {
//			likeListRepository.deleteByUserAndBoard(currentUser, boardEntity);
//			isLiked = false;
//		} else {
//			LikeListEntity likeListEntity = new LikeListEntity();
//			likeListEntity.setUser(currentUser);
//			likeListEntity.setBoard(boardEntity);
//			likeListRepository.save(likeListEntity);
//			isLiked = true;
//		}
//
//		int likeCount = likeListRepository.countByBoard(boardEntity);
//
//		Map<String, Object> response = new HashMap<>();
//		response.put("isLiked", isLiked);
//		response.put("likeCount", likeCount);
//
//		return response;
//	}

}
