package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.jwt.JwtToken;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.KakaoOAuthService;
import com.example.demo.service.NaverOAuthService;
import com.example.demo.service.RedisService;
import com.example.demo.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
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
	private final JwtTokenProvider jwtTokenProvider;

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
//	@PostMapping("/login")
//	public JwtToken login(@RequestBody LoginDTO loginDTO) {
//		String userId = loginDTO.getUserId();
//		String password = loginDTO.getPassword();
//		JwtToken tokenInfo = userService.login(userId, password);
//
//		return tokenInfo;
//	}
	@PostMapping("/login")
	public ResponseEntity<JwtToken> login(@RequestBody LoginDTO loginDTO) {
		String userId = loginDTO.getUserId();
		String password = loginDTO.getPassword();
		JwtToken tokenInfo = userService.login(userId, password);

		// 리프레시 토큰을 Redis에 저장
		redisService.storeRefreshToken(userId, tokenInfo.getRefreshToken());

		// 클라이언트에는 액세스 토큰만 반환
		return ResponseEntity.ok(new JwtToken(tokenInfo.getAccessToken(), null));
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
	public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
		String accessToken = request.get("accessToken");
		if (accessToken == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Access token is missing");
		}

		JwtToken newToken = redisService.refreshAccessToken(accessToken);
		return ResponseEntity.ok(newToken);
	}

	// @RequestHeader 헤더에서 액세스토큰 추출
//	@PostMapping("/refreshToken")
//	public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authorizationHeader) {
//		// Authorization 헤더에서 "Bearer " 부분을 제거하고 토큰만 추출
//		String accessToken = authorizationHeader.replace("Bearer ", "");
//
//		if (accessToken == null || accessToken.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Access token is missing");
//		}
//
//		JwtToken newToken = redisService.refreshAccessToken(accessToken);
//		return ResponseEntity.ok(newToken);
//	}


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
//	@PostMapping("/logout")
//	public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader) {
//		return userService.logout(authorizationHeader);
//	}
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader) {
		String accessToken = authorizationHeader.replace("Bearer ", "");
		if (accessToken == null || accessToken.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Access token is missing");
		}

		try {
			if (jwtTokenProvider.validateToken(accessToken)) {
				String userId = jwtTokenProvider.getUserIdFromToken(accessToken);
				redisService.deleteRefreshToken(userId);
				redisService.blacklistAccessToken(accessToken);
				return ResponseEntity.ok("로그아웃 성공!");
			}
		} catch (ExpiredJwtException e) {
			String userId = e.getClaims().getSubject();
			redisService.deleteRefreshToken(userId);
			redisService.blacklistAccessToken(accessToken);
			return ResponseEntity.ok("로그아웃 성공! (만료된 토큰)");
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
	}
}
