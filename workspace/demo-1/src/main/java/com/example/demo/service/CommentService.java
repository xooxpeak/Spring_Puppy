package com.example.demo.service;

import com.example.demo.config.SecurityUtil;
import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private CommentMapper commentMapper;



	// 댓글 작성
	public Long saveComment(Long boardId, Long userId, CommentDTO commentDTO) {

		// 현재 사용자의 ID를 가져옴
		String currentUserId = SecurityUtil.getCurrentUserId();

		// 로그인된 사용자가 없는 경우 예외 처리
		if (currentUserId == null || "anonymousUser".equals(currentUserId)) {
			throw new RuntimeException("로그인한 사용자가 없습니다.");
		}

		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		BoardEntity board = boardRepository.findById(boardId)
				.orElseThrow(() -> new IllegalArgumentException("Board not found"));

		// CommentEntity 생성 및 저장
		CommentEntity commentEntity = commentMapper.commentToEntity(commentDTO);
		commentEntity.setUser(user);
		commentEntity.setBoard(board);
		commentRepository.save(commentEntity);

//		CommentEntity comment = new CommentEntity();
//		comment.setBoardId(boardId);
//		comment.setUserId(userId);
//		comment.setCommentDate(new Date());
//		comment.setComment(commentDTO.getComment());
//		CommentEntity savedComment = commentRepository.save(comment);

		return commentEntity.getId();


	}


	public List<CommentDTO> getCommentsByBoard(Long boardId) {
		// 게시판 정보 찾기
		BoardEntity board = boardRepository.findById(boardId)
				.orElseThrow(() -> new IllegalArgumentException("Board not found"));

		// 댓글 목록 조회 및 변환
		return commentRepository.findByBoard(board).stream()
				.map(commentMapper::commentToDto)
				.collect(Collectors.toList());
	}

}
