package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.jwt.JwtToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.KakaoOAuthService;
import com.example.demo.service.RedisService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	RedisService redisService;

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
	public JwtToken login(@RequestBody LoginDTO loginDTO) {
		String userId = loginDTO.getUserId();
		String password = loginDTO.getPassword();
		JwtToken tokenInfo = userService.login(userId, password);
		return tokenInfo;
	}


	/**
	 기능 : 카카오로그인
	 url : /kakaoLogin
	 request Data :
	 Response Data : 로그인 성공
	 */
	@PostMapping("/kakaoLogin")
	public ResponseEntity<Map<String, String>> kakaoLogin(@RequestBody String code) {
		JSONParser parser = new JSONParser();
		String kakaoCode;
		try {
			JSONObject object = (JSONObject) parser.parse(code);
			kakaoCode = (String) object.get("code");
		} catch (ParseException e) {
			return ResponseEntity.badRequest().build();
		}

		String accessToken = kakaoOAuthService.getAccessToken(kakaoCode);
		Map<String, Object> userInfo = kakaoOAuthService.getUserInfo(accessToken);
		JwtToken jwtToken = userService.kakaoLogin(userInfo);

		Map<String, String> response = new HashMap<>();
		response.put("accessToken", jwtToken.getAccessToken());

		return ResponseEntity.ok(response);
	}


	/**
	 기능 : Redis 사용해 Token 갱신
	 url : /refreshToken
	 request Data :
	 Response Data : 로그인 성공
	 */
	// Redis
	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshToken(@RequestBody JwtToken jwtToken) {
		String refreshToken = jwtToken.getRefreshToken();
		// String currentUserId = SecurityUtil.getCurrentUserId();  // 현재 로그인된 사용자

		JwtToken newToken = redisService.refreshAccessToken(refreshToken);

		if (newToken != null) {
			return ResponseEntity.ok(newToken);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
		}

		// Redis에서 저장된 Refresh Token 가져오기
		//String redisRefreshToken = userService.getRedisRefreshToken(currentUserId);

		// 클라이언트의 Refresh Token과 Redis의 Refresh Token이 일치하는지 확인
		//if (refreshToken.equals(redisRefreshToken)) {
			// 새로운 Access Token 발급
		//	String newAccessToken = userService.generateNewAccessToken(currentUserId);

			// 새로운 Access Token을 반환
		//	return ResponseEntity.ok(new JwtToken(newAccessToken));
		//} else {
		//	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
		//}
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
