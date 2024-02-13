package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PuppyNoteController {

	/**
	  기능 : 알림장 작성  ( 훈련사 계정 )
	  url : /createPuppyNote
	  request data : 날짜, 식사 및 간식(아침·점심·저녁·간식), 배변 상태(횟수·상태), 기분 및 컨디션, 오늘 하루
	  response data : 알림장 작성 성공
	 */
	
	@PostMapping("/createPuppyNote")
	public Object createPuppyNote() {
		return null;
	}
	
	
	/**
	  기능 : 알림장 수정 ( 훈련사 계정 )
	  url : /updatePuppyNote
	  request data : 특정 날짜의 식사 및 간식(아침·점심·저녁·간식), 배변 상태(횟수·상태), 기분 및 컨디션, 오늘 하루
	  response data : 알림장 수정 성공
	 */
	
	@PutMapping("/updatePuppyNote")
	public Object updatePuppyNote() {
		return null;
	}
	
	
	/**
	  기능 : 알림장 삭제 ( 훈련사 계정 )
	  url : /deletePuppyNote
	  request data : 특정 날짜의 알림장 삭제
	  response data : 알림장 삭제 성공
	 */
	
	@DeleteMapping("/deletePuppyNote")
	public Object deletePuppyNote() {
		return null;
	}
	
	
	/**
	  기능 : 알림장 목록
	  url : /puppyNoteList
	  request data : 
	  response data : 알림장 목록
	 */
	
	@GetMapping("/puppyNote")
	public Object puppyNote() {
		return null;
	}
	
	
	/**
	  기능 : 알림장 상세보기
	  url : /puppyNoteDetail
	  request data : 특정 날짜의 알림장 상세보기
	  response data : 해당 날짜, 식사 및 간식(아침·점심·저녁·간식), 배변 상태(횟수·상태), 기분 및 컨디션, 오늘 하루
	 */
	
	@GetMapping("/puppyNoteDetail")
	public Object puppyForm() {
		return null;
	}
	
	
}
