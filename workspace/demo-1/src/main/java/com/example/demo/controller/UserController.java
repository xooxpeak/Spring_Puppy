package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.jwt.JwtToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.KakaoOAuthService;
import com.example.demo.service.NaverOAuthService;
import com.example.demo.service.RedisService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@Controller   // 웹페이지와 통신
//@ResponseBody   // data만 전달

@Slf4j
@RestController  // 두 개의 역할을 처리해줌
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/n")
public class UserController {

	private final UserService userService;
	private final RedisService redisService;
	private final UserRepository userRepository;
	private final KakaoOAuthService kakaoOAuthService;
	private final NaverOAuthService naverOAuthService;

//	@Autowired
//	UserService userService;
//
//	@Autowired
//	RedisService redisService;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	KakaoOAuthService kakaoOAuthService;
//
//	@Autowired
//	NaverOAuthService naverOAuthService;

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
	 기능 : 네이버로그인
	 url : /naverLogin
	 request Data :
	 Response Data : 로그인 성공
	 */
	@PostMapping("/naverLogin")
	public ResponseEntity<Map<String, String>> naverLogin(@RequestBody Map<String, String> request) {
		String code = request.get("code");
		String state = request.get("state");

		if (code == null || state == null) {
			return ResponseEntity.badRequest().build();
		}

		String accessToken = naverOAuthService.getAccessToken(code);
		Map<String, Object> userInfo = naverOAuthService.getUserInfo(accessToken);
		JwtToken jwtToken = userService.naverLogin(userInfo);

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
//		// 클라이언트에서 전달된 Reresh Token을 받아옴
//		String refreshToken = jwtToken.getRefreshToken();
//		// RedisService의 refreshAccessToken을 사용하여 새로운 Access Token 발급
//		JwtToken newToken = redisService.refreshAccessToken(refreshToken);

		String refreshToken = jwtToken.getRefreshToken();
		if (refreshToken == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refresh token is missing");
		}
		
		System.out.println("액세스 토큰 갱신 요청");
		System.out.println("Received jwtToken: " + jwtToken);
		System.out.println("Received refreshToken: " + jwtToken.getRefreshToken());

		JwtToken newToken = redisService.refreshAccessToken(jwtToken.getRefreshToken());

		// RedisService에서 반환된 결과를 반환 = 새로운 Access Token 발급하여 반환
		if (newToken != null) {
			return ResponseEntity.ok(newToken);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 Refresh Token");
		}

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
