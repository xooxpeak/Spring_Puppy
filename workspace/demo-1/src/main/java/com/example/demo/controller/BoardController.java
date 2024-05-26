package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/n")
public class BoardController {

	@Autowired
	private BoardService boardService;

	
	/**
	  기능 : 게시글 작성
	  url : /createBoard
	  request data : 글제목, 글내용, 사진, 동영상, 파일, 작성날짜, 작성시간
	  response data : 게시글 작성 성공
	 */
	@PostMapping("/createBoard")
	public BoardDTO createBoard(@RequestBody BoardDTO boardDTO) {
		return boardService.createBoard(boardDTO);
	}
	
	
	/**
	  기능 : 게시글 수정 ( 글 작성한 계정 )
	  url : /updateBoard
	  request data : 글제목, 글내용, 파일
	  response data : 게시글 수정 성공
	 */
	@PutMapping("/updateBoard")
	public BoardDTO updateBoard(@RequestParam(name = "id") Long id, @RequestBody BoardDTO boardDTO) {
		// 게시글 ID에 해당하는 게시물을 찾아옴
		// BoardDTO updatedBoard = boardService.updateBoard(id, boardDTO);
		return boardService.updateBoard(id, boardDTO);
	}
	
	
	/**
	  기능 : 게시글 삭제 ( 글 작성한 계정 )
	  url : /deleteBoard
	  request data : 특정 날짜의 게시글 삭제
	  response data : 게시글 삭제 성공
	 */
	@DeleteMapping("/deleteBoard")
	public void deleteBoard(@RequestParam(name = "id") Long id) {
		boardService.deleteBoard(id);
	}
	
	
	/**
	  기능 : 게시글 목록
	  url : /board
	  request data : 
	  response data : 게시글 목록
	 */
//	@GetMapping("/board")
//	public List<BoardDTO> board() {
//		return boardService.board();
//	}


	/**
	 기능 : 게시글 목록 및 상세보기
	 url : /board
	 request data : id (optional)
	 response data : 게시글 목록 또는 특정 게시글 상세정보
	 */
	@GetMapping("/board")
	public Object board(@RequestParam(name = "id", required = false) Long id) {
		if (id == null) {
			// 게시글 목록 반환
			return boardService.board();
		} else {
			// 특정 게시글 상세 정보 반환
//			Map<String, Object> response = new HashMap<>();
//			response.put("boardDetail", boardService.boardDetail(id));
//			return response;
			return boardService.boardDetail(id);
		}
	}




	/**
	  기능 : 게시글 상세보기
	  url : /boardDetail
	  request data : 특정 날짜의 글제목, 글내용, 사진, 동영상, 파일, 작성날짜, 작성시간
	  response data : 해당 날짜의 글제목, 글내용, 사진, 동영상, 파일, 작성날짜, 작성시간
	 */
//	@GetMapping("/boardDetail")
//	public BoardDTO boardDetail(@RequestParam Long id) {
//		return boardService.boardDetail(id);
//	}
//	@GetMapping("/boardDetail")
//	public Map<String, Object> boardDetail(@RequestParam Long id) {
//		Map<String, Object> response = new HashMap<>();
//		response.put("boardDetail", boardService.boardDetail(id));
//		return response;
//	}
	
	
	/**
	  기능 : 댓글 작성
	  url : /createComment
	  request data : 댓글 작성 아이디, 강아지 이름, 내용, 날짜, 시간
	  response data : 댓글 작성 성공
	 */
	@PostMapping("/createComment")
	public Object createComment() {
		return null;
	}
	
	
	/**
	  기능 : 댓글 수정 ( 댓글 작성한 계정 )
	  url : /updateComment
	  request data : 댓글 작성 아이디, 강아지 이름, 내용, 날짜, 시간
	  response data : 댓글 수정 성공
	 */
	@PutMapping("/updateComment")
	public Object updateComment() {
		return null;
	}
	
	
	/**
	  기능 : 댓글 삭제 ( 댓글 작성한 계정 )
	  url : /deleteComment
	  request data : 댓글 작성 아이디, 강아지 이름, 내용, 날짜, 시간 삭제
	  response data : 댓글 삭제 성공
	 */
	@DeleteMapping("/deleteComment")
	public Object deleteComment() {
		return null;
	}
	
	
	/**
	  기능 : 댓글 보기
	  url : /comment
	  request data : 
	  response data : 
	 */
	@GetMapping("/comment")
	public Object comment() {
		return null;
	}
	
	
	/**
	  기능 : 좋아요
	  url : /userLike
	  request data : 
	  response data : 좋아요 성공
	 */
	@GetMapping("/userLike")
	public Object userLike() {
		return null;
	}
	
	
	/**
	  기능 : 좋아요 중복 체크
	  url : /dupUserLike
	  request data : 
	  response data : 좋아요 중복 확인
	 */
	@GetMapping("/dupUserLike")
	public Object dupUserLike() {
		return null;
	}
	
	
	/**
	  기능 : 좋아요 한 게시글 목록
	  url : /userLikeList
	  request data : 
	  response data : 좋아요 한 게시글
	 */
	@GetMapping("/userLikeList")
	public Object userLikeList() {
		return null;
	}
	
	
	
	/*
	 나중에 추가
	 1. 대댓글 기능
	 2. 좋아요 취소 기능
	*/
	
		
}
