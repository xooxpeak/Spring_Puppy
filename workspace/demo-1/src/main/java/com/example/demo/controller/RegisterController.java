package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/auth/n")
public class RegisterController {
	
	@Autowired
	UserService userService;

	/**
	기능 : 회원가입
	url : /register
	request Data : 아이디, 비밀번호, 비밀번호 확인, 이메일, 이름, 생년월일, 핸드폰번호, 주소
	Response Data : 이메일 인증, 회원가입 성공
	 */
	
	@PostMapping("/register")
	public Object register() {
		return null;
	}
	
	
	/**
	기능 : 회원가입 이메일 인증
	url : /registerEmail
	request Data : 
	Response Data : 이메일 인증 성공
	 */
	
	@GetMapping("/registerEmail")
	public Object registerEmail() {
		return null;
	}
	
	
	/**
	기능 : 아이디 중복 체크
	url : /dupIdCheck
	request Data : 아이디
	Response Data : 사용 가능한 아이디 or 중복된 아이디
	 * @throws Exception 
	 */
	
	@PostMapping("/dupIdCheck")
	public Object dupIdCheck(@RequestParam(value = "userId") String userId) throws Exception {
		return userService.idCheck(userId);
	}
	
}
