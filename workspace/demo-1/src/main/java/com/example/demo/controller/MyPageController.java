package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPageController {
	
	 /**
	  기능 : 회원 정보 수정
	  url : /updateMember
	  request data : 
	  response data : 회원 정보 수정 성공
	 */
	
	 @PutMapping("/updateMember")
	    public Object updateMember() {
		 	return null;
	    }
	 
	 
	 /**
	  기능 : 강아지 정보 수정
	  url : /updatePuppy
	  request data : 
	  response data : 강아지 정보 수정 성공
	  */
	 
	 @PutMapping("/updatePuppy")
	 public Object updatePuppy() {
		 return null;
	 }
	 
	 
	 /**
	  기능 : 좋아요 글 목록
	  url : /likeList
	  request data : 
	  response data : 좋아요 글 목록
	  */
	 
	 @GetMapping("/likeList")
	 public Object likeList() {
		 return null;
	 }
	 
	 
	 /**
	  기능 : 내가 쓴 글 목록
	  url : /createList
	  request data : 
	  response data : 내가 쓴 글 목록
	  */
	 
	 @GetMapping("/createList")
	 public Object createList() {
		 return null;
	 }
	 
	 
	 /**
	  기능 : 회원 탈퇴
	  url : /deleteId
	  request data : 탈퇴할 회원 정보
	  response data : 회원 탈퇴
	  */
	 
	 @DeleteMapping("/deleteId")
	 public Object deleteId() {
		 return null;
	 }
	 
}
