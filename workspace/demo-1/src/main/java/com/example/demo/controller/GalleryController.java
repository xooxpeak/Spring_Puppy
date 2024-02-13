package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GalleryController {

	/**
	  기능 : 사진첩 작성  ( 훈련사 계정 )
	  url : /createGallery
	  request data : 날짜(글제목), 글 내용, 작성 날짜, 작성 시간
	  response data : 사진첩 작성 성공
	 */
	
	@PostMapping("/createGallery")
	public Object createGallery() {
		return null;
	}
	
	
	/**
	  기능 : 사진첩 수정 ( 훈련사 계정 )
	  url : /updateGallery
	  request data : 날짜(글제목), 글 내용, 작성 날짜, 작성 시간
	  response data : 사진첩 수정 성공
	 */
	
	@PutMapping("/updateGallery")
	public Object updateGallery() {
		return null;
	}
	
	
	/**
	  기능 : 사진첩 삭제 ( 훈련사 계정 )
	  url : /deleteGallery
	  request data : 특정 날짜의 사진첩 삭제
	  response data : 사진첩 삭제 성공
	 */
	
	@DeleteMapping("/deleteGallery")
	public Object deleteGallery() {
		return null;
	}
	
	
	/**
	  기능 : 사진첩 목록
	  url : /gallery
	  request data : 
	  response data : 사진첩 목록
	 */
	
	@GetMapping("/gallery")
	public Object gallery() {
		return null;
	}
	
	
	/**
	  기능 : 사진첩 상세보기
	  url : /galleryDetail
	  request data : 특정 날짜(글제목), 글 내용, 작성 날짜, 작성 시간
	  response data : 해당 날짜(글제목), 글 내용, 작성 날짜, 작성 시간
	 */
	
	@GetMapping("/galleryDetail")
	public Object galleryDetail() {
		return null;
	}
		
}
