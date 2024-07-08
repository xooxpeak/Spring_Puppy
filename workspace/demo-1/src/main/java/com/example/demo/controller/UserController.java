package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.jwt.JwtToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.KakaoOAuthService;
import com.example.demo.service.NaverOAuthService;
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

	@Autowired
	NaverOAuthService naverOAuthService;

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
	// "/login/oauth2/code/naver"
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

	// HttpRequestMethodNotSupportedException: Request method 'GET' is not supported
//	@PostMapping("/naverLogin")
//	public ResponseEntity<Map<String, String>> naverLogin(@RequestBody String code) {
//		System.out.println("code: " + code);
//
//		JSONParser parser = new JSONParser();
//		String naverCode;
//		try {
//			JSONObject object = (JSONObject) parser.parse(code);
//			naverCode = (String) object.get("code");
//		} catch (ParseException e) {
//			return ResponseEntity.badRequest().build();
//		}
//
//		String accessToken = naverOAuthService.getAccessToken(naverCode);
//		Map<String, Object> userInfo = naverOAuthService.getUserInfo(accessToken);
//		JwtToken jwtToken = userService.naverLogin(userInfo);
//
//		Map<String, String> response = new HashMap<>();
//		response.put("accessToken", jwtToken.getAccessToken());
//
//		return ResponseEntity.ok(response);
//	}




	/**
	 기능 : Redis 사용해 Token 갱신
	 url : /refreshToken
	 request Data :
	 Response Data : 로그인 성공
	 */
	// Redis
	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshToken(@RequestBody JwtToken jwtToken) {
		// String currentUserId = SecurityUtil.getCurrentUserId();  // 현재 로그인된 사용자
		
		// 클라이언트에서 전달된 Reresh Token을 받아옴
		String refreshToken = jwtToken.getRefreshToken();

		// RedisService의 refreshAccessToken을 사용하여 새로운 Access Token 발급
		JwtToken newToken = redisService.refreshAccessToken(refreshToken);

		// RedisService에서 반환된 결과를 반환 = 새로운 Access Token 발급하여 반환
		if (newToken != null) {
			return ResponseEntity.ok(newToken);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 Refresh Token");
		}

		// 클라이언트의 Refresh Token과 Redis에 저장된 Refresh Token을 비교
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
