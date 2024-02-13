package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindController {
	
	/**
	기능 : 아이디 찾기 
	url : /findId
	request Data : 이름, 이메일
	Response Data : 아이디
	 */
	
	@GetMapping("/findId")
	public Object findId() {
		return null;
	}
	
	
	/**
	기능 : 비밀번호 찾기
	url : /findPw
	request Data : 이름, 이메일, 인증번호(?)
	Response Data : 비밀번호 찾기
	 */
	
	@GetMapping("/findPw")
	public Object findPw() {
		return null;
	}
	
	
	/**
	기능 : 비밀번호 재설정 
	url : /resetPw
	request Data : 새로운 비밀번호
	Response Data : 비밀번호 재설정 성공
	 */
	
	@PostMapping("/resetPw")
	public Object resetPw() {
		return null;
	}
	
}
