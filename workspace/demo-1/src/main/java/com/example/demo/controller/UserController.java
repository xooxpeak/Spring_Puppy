package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.jwt.JwtToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.KakaoOAuthService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@Controller   // 웹페이지와 통신
//@ResponseBody   // data만 전달

@Slf4j
@RestController  // 두 개의 역할을 처리해줌
@RequestMapping("/api/v1/auth/n")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	KakaoOAuthService kakaoOAuthService;

	/**
	 기능 : 로그인
	 url : /login
	 request Data : 아이디, 비밀번호
	 Response Data : 로그인 성공
	 */

	@PostMapping("/login")
//	public JwtToken login(@RequestParam(value="userId") String userId, @RequestParam(value="password") String password) {
//		return userService.login(userId, password);
//	}
	public JwtToken login(@RequestBody LoginDTO loginDTO) {
		String userId = loginDTO.getUserId();
		String password = loginDTO.getPassword();
		JwtToken tokenInfo = userService.login(userId, password);
		return tokenInfo;
	}


//	@PostMapping("/kakaoLogin")
//	public JwtToken kakaoLogin(@RequestBody String code) {
//		String accessToken = KakaoOAuthService.getAccessToken(code);
//		Map<String, Object> userInfo = kakaoOAuthService.getUserInfo(accessToken);
//		JwtToken jwtToken = userService.kakaoLogin(userInfo);
//		Map<String, String> response = new HashMap<>();
//		response.put("accessToken", jwtToken.getAccessToken());
//		return jwtToken;
//	//	return userService.kakaoLogin(userInfo);
//
//	}

	@PostMapping("/kakaoLogin")
	public ResponseEntity<Map<String, String>> kakaoLogin(@RequestBody String code) {
		String accessToken = kakaoOAuthService.getAccessToken(code);
		Map<String, Object> userInfo = kakaoOAuthService.getUserInfo(accessToken);
		JwtToken jwtToken = userService.kakaoLogin(userInfo);

		Map<String, String> response = new HashMap<>();
		response.put("access_token", jwtToken.getAccessToken());

		return ResponseEntity.ok(response);
	}


	@PostMapping("/test")
	public int test(){
		return 1;
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
