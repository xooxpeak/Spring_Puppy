package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PuppyController {
	
	/**
	  기능 : 강아지 등록 
	  url : /puppy
	  request data : 이름, 성별, 중성화여부, 생년월일, 종, 알러지 유무, 성격, 자기소개
	  response data : 강아지 등록 성공
	 */
	
	@PostMapping("/puppy")
	public Object puppy() {
		return null;
	}
	
}	
