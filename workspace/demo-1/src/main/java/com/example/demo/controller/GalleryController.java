package com.example.demo.controller;

import com.example.demo.dto.GalleryDTO;
import com.example.demo.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/y")
public class GalleryController {

	@Autowired
	GalleryService galleryService;

	/**
	 * 기능 : 사진첩 작성  ( 훈련사 계정 )
	 * url : /createGallery
	 * request data : 날짜(글제목), 글 내용, 작성 날짜, 작성 시간
	 * response data : 사진첩 작성 성공
	 */
	@PostMapping("/createGallery")
	public List<GalleryDTO> createGallery(@RequestPart (value = "file", required = false) List<MultipartFile> uploadFiles) {
		return galleryService.createGallery(uploadFiles);
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
	public List<GalleryDTO> gallery() {
		return galleryService.galleryList();
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
