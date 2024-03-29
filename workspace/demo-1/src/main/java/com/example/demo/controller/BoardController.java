package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

	
	/**
	  기능 : 게시글 작성
	  url : /createBoard
	  request data : 글제목, 글내용, 사진, 동영상, 파일, 작성날짜, 작성시간
	  response data : 게시글 작성 성공
	 */
	
	@PostMapping("/createBoard")
	public Object createBoard() {
		return null;
	}
	
	
	/**
	  기능 : 게시글 수정 ( 글 작성한 계정 )
	  url : /updateBoard
	  request data : 글제목, 글내용, 사진, 동영상, 파일, 작성날짜, 작성시간
	  response data : 게시글 수정 성공
	 */
	
	@PutMapping("/updateBoard")
	public Object updateBoard() {
		return null;
	}
	
	
	/**
	  기능 : 게시글 삭제 ( 글 작성한 계정 )
	  url : /deleteBoard
	  request data : 특정 날짜의 게시글 삭제
	  response data : 게시글 삭제 성공
	 */
	
	@DeleteMapping("/deleteBoard")
	public Object deleteBoard() {
		return null;
	}
	
	
	/**
	  기능 : 게시글 목록
	  url : /board
	  request data : 
	  response data : 게시글 목록
	 */
	
	@GetMapping("/board")
	public Object board() {
		return null;
	}
	
	
	/**
	  기능 : 게시글 상세보기
	  url : /boardDetail
	  request data : 특정 날짜의 글제목, 글내용, 사진, 동영상, 파일, 작성날짜, 작성시간
	  response data : 해당 날짜의 글제목, 글내용, 사진, 동영상, 파일, 작성날짜, 작성시간
	 */
	
	@GetMapping("/boardDetail")
	public Object galleryDetail() {
		return null;
	}
	
	
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
