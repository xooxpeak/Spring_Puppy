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


@Service
public class LikeListService {
	
	@Autowired
	private LikeListRepository likeListRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardRepository boardRepository;

	public ResDTO insert(Long boardId, String userId) {

		System.out.println(userId);
		System.out.println(boardId);

		UserEntity user = userRepository.findByUserId(userId);
		BoardEntity board = boardRepository.findById(boardId).orElse(null);

		if (user == null || board == null) {
			return ResDTO.builder()
					.code("404")
					.message("유저 또는 게시물을 찾을 수 없습니다.")
					.build();
		}

		// 이미 좋아요 했다면?
		if (likeListRepository.findByUserAndBoard(user, board).isPresent()) {
			return ResDTO.builder()
					.code("409")
					.message("이미 좋아요를 눌렀습니다.")
					.build();
		}

		// 좋아요 안되어있다면?
		LikeListEntity likeListEntity = LikeListEntity.builder()
				.board(board)
				.user(user)
				.build();
		likeListRepository.save(likeListEntity);

		return ResDTO.builder()
				.code("200")
				.message("좋아요 성공")
				.build();
	}


	
}
