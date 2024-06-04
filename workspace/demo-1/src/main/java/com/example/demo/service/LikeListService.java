package com.example.demo.service;

import com.example.demo.dto.ResDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.LikeListEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.LikeListRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class LikeListService {
	
	@Autowired
	private LikeListRepository likeListRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardRepository boardRepository;

	// LikeService에서만 좋아요 기능을 처리
	// LikeListService의 insertLike 메서드에서 boardEntity의 userLike 필드를 업데이트 (좋아요 수)
	@Transactional
	public ResDTO insertLike(Long boardId, String userId) {

		UserEntity user = userRepository.findByUserId(userId);
		BoardEntity board = boardRepository.findById(boardId).orElse(null);

		if (user == null || board == null) {
			return ResDTO.builder()
					.code("404")
					.message("유저 또는 게시물을 찾을 수 없습니다.")
					.build();
		}

		Optional<LikeListEntity> existingLike = likeListRepository.findByUserAndBoard(user, board);

		// 이미 좋아요 했다면? && 좋아요 수가 0보다 크다면? (음수 되는 것 방지)
		if (existingLike.isPresent() && board.getUserLike() > 0) {
			// 좋아요 삭제
			//	likeListRepository.deleteByUserAndBoard(user, board);
			likeListRepository.delete(existingLike.get());
			board.setUserLike(board.getUserLike() - 1);  // 좋아요 수 감소
		} else {
		// 좋아요 안되어있다면?
			// 좋아요 추가
			LikeListEntity likeListEntity = LikeListEntity.builder()
					.board(board)
					.user(user)
					.build();
			likeListRepository.save(likeListEntity);  // 추가한 좋아요를 저장
			board.setUserLike(board.getUserLike() + 1); // 좋아요 수 증가
		}

		boardRepository.save(board);  // 업데이트된 좋아요 수 저장

		return ResDTO.builder()
					.code("200")
					.message(existingLike.isPresent() ? "좋아요 취소 성공" : "좋아요 성공")
					.build();
	}

	// 특정 게시글에 대해 현재 사용자가 해당 게시글을 좋아요했는지 여부와 총 좋아요 수를 조회하는 메서드
		public Map<String, Object> getLikeInfo(Long boardId, String userId) {
			// 사용자 조회
			UserEntity user = userRepository.findByUserId(userId);
			// 게시글 조회
			BoardEntity board = boardRepository.findById(boardId)
					.orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

			// 사용자가 해당 게시글을 좋아요했는지 여부 확인
			// 좋아요가 존재하면 true, 존재하지 않으면 false
			boolean isLiked = likeListRepository.findByUserAndBoard(user, board).isPresent();

			// board entity에서 직접 저장된 좋아요 수를 가져옴
			int likeCount = board.getUserLike();

			// 좋아요 여부(boolean)와 좋아요 수(int)를 함께 저장하기 위해 HashMap을 사용
			// 결과를 저장할 맵 생성
			Map<String, Object> likeInfo = new HashMap<>();
			likeInfo.put("isLiked", isLiked);
			likeInfo.put("likeCount", likeCount);

			return likeInfo;
		}

	}


//		// 이미 좋아요 했다면?
//		if (likeListRepository.findByUserAndBoard(user, board).isPresent()) {
//			return ResDTO.builder()
//					.code("409")
//					.message("이미 좋아요를 눌렀습니다.")
//					.build();
//		}
//
//		// 좋아요 안되어있다면?
//		LikeListEntity likeListEntity = LikeListEntity.builder()
//				.board(board)
//				.user(user)
//				.build();
//		likeListRepository.save(likeListEntity);
//
//		return ResDTO.builder()
//				.code("200")
//				.message("좋아요 성공")
//				.build();
//	}