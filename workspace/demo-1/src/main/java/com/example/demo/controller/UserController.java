package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller   // 웹페이지와 통신
//@ResponseBody   // data만 전달
@RestController  // 두 개의 역할을 처리해줌
public class UserController {
	
	/**
	기능 : 로그인
	url : /login
	request Data : 아이디, 비밀번호
	Response Data : 로그인 성공
	*/
	 
	@PostMapping("/login")
	public Object login() {
		return null;
	}
	
	
	/**
	기능 : 로그아웃
	url : /logout
	request Data : 
	Response Data : 로그아웃
	 */
	
	@GetMapping("/logout")
	public Object logout() {
		return null;
	}
	
	

}
