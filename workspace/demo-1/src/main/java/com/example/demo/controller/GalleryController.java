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

//	// 업로드 할 위치
//	@Value("${part.upload.path}")
//	private String uploadPath;

	/**
	 * 기능 : 사진첩 작성  ( 훈련사 계정 )
	 * url : /createGallery
	 * request data : 날짜(글제목), 글 내용, 작성 날짜, 작성 시간
	 * response data : 사진첩 작성 성공
	 */
	@PostMapping("/createGallery")
	public List<GalleryDTO> createGallery(@RequestParam("uploadFiles") MultipartFile[] uploadFiles) {
		return galleryService.createGallery(uploadFiles);
	}
//	public ResponseEntity<List<UploadResDTO>> createGallery(MultipartFile[] uploadFiles) {
//
//		List<UploadResDTO> uploadResDTOList = new ArrayList<>();
//
//		// 업로드 할 위치
////		String uploadPath = "C:/Users/xooxpeak/Desktop/과외/Upload";
//
//		for (MultipartFile uploadFile: uploadFiles) {
//
//			// 이미지 파일만 업로드
//			if (!Objects.requireNonNull(uploadFile.getContentType()).startsWith("image")) {
//				log.warn("this file is not image type");
//				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//			}
//
//			// 파일명: 모든 경로를 포함한 파일이름
//			String originalName = uploadFile.getOriginalFilename();
//			// 원본 파일 이름이 null이 아닌지 확인
//			assert originalName != null;
//			// 마지막으로온 "/"부분으로부터 +1 해준 부분부터 출력
//			String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
//			log.info("fileName" + fileName);
//
//			//날짜 폴더 생성
//			String folderPath = galleryService.makeFolder();
//			//UUID
//			String uuid = UUID.randomUUID().toString();
//			//저장할 파일 이름 중간에 "_"를 이용하여 구분
//			String saveName = uploadPath + File.separator + folderPath +File.separator + uuid + "_" + fileName;
//
//			//Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
//			Path savePath = Paths.get(saveName);
//
//			try{
//				// transferTo(file) : uploadFile에 파일을 업로드 하는 메서드
//				uploadFile.transferTo(savePath);
//				uploadResDTOList.add(new UploadResDTO(fileName, uuid, folderPath));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//        }
//		// 모든 파일 업로드가 성공한 경우
//		return new ResponseEntity<>(uploadResDTOList, HttpStatus.OK);
//
//	}


	
	
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
