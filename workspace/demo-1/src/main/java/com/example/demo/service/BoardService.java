package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.mapper.BoardMapper;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	private UserRepository userRepository;

	public BoardDTO createBoard(BoardDTO boardDTO) {
//		 현재 로그인한 사용자의 ID를 가져옴
//		UserEntity user = userRepository.findById(SecurityUtil.getCurrentUserId())
//				.orElseThrow() -> new RuntimeException("로그인 유저 정보가 없습니다"));
		// 새로운 게시글 생성: 클라이언트로부터 받은 DTO를 서비스로 전달하기 위해 entity로 변환
		BoardEntity boardEntity = BoardMapper.instance.boardToEntity(boardDTO);
		// 게시글에 작성자 정보 설정
		boardEntity.setUserId(boardEntity.getUserId());
		// 게시글 저장
		boardRepository.save(boardEntity);

		// 클라이언트로 응답을 보내기 위해 entity를 DTO로 변환
		return BoardMapper.instance.boardToDTO(boardEntity);

	}
}
