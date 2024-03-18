package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.jwt.JwtToken;
import com.example.demo.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller   // 웹페이지와 통신
//@ResponseBody   // data만 전달
@RestController  // 두 개의 역할을 처리해줌
@RequiredArgsConstructor
public class UserController {

	@Autowired
	UserDetailService userDetailService;
	
	/**
	기능 : 로그인
	url : /login
	request Data : 아이디, 비밀번호
	Response Data : 로그인 성공
	*/
	@PostMapping("/login")
	public JwtToken login(@RequestBody UserDTO userDTO) {
		String username = userDTO.getUserId();
		String password = userDTO.getPassword();

		JwtToken jwtToken = userDetailService.login(username, password);

		return jwtToken;
	}

	@PostMapping("/test")
	public String test() {
		return "success";
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
