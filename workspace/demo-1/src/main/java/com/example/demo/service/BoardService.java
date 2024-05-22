package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.mapper.BoardMapper;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
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

	// 게시글 작성
	public BoardDTO createBoard(BoardDTO boardDTO) {
//		 현재 로그인한 사용자의 ID를 가져옴
//		UserEntity user = userRepository.findById(SecurityUtil.getCurrentUserId())
//				.orElseThrow() -> new RuntimeException("로그인 유저 정보가 없습니다"));

//		// 새로운 게시글 생성: 클라이언트로부터 받은 DTO를 서비스로 전달하기 위해 entity로 변환
//		BoardEntity boardEntity = BoardMapper.instance.boardToEntity(boardDTO);
//		// 게시글에 작성자 정보 설정
//		boardEntity.setUserId(boardEntity.getUserId());

		// 새로운 게시글 생성: 클라이언트로부터 받은 DTO를 엔티티로 변환하고 필요한 필드 설정
		BoardEntity boardEntity = BoardEntity.builder()
				.userId(boardDTO.getUserId())
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
	public BoardDTO boardDetail(Long id) {
		Optional<BoardEntity> board = boardRepository.findById(id);
		if (board.isPresent()) {
			return BoardMapper.instance.boardToDTO(board.get());
		} else {
			throw new RuntimeException("게시글을 찾을 수 없습니다.");
		}
	}

}
