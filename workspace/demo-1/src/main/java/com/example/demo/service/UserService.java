package com.example.demo.service;

import com.example.demo.dto.ResDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserRole;
import com.example.demo.jwt.JwtToken;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RedisRepository;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

	@Autowired
	AuthenticationManagerBuilder authenticationManagerBuilder;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private HttpServletResponse response;

	/**
	 *
	 기능 : 아이디 중복 검사
	 */
	// 아이디 중복 검사 버튼
	public Object idCheck(String userId) throws Exception {
		// 사용자 아이디가 빈 문자열인 경우 예외를 던짐
		if(userId.equals("")) {
			throw new Exception();
		}
		// UserRepository를 사용하여 주어진 사용자 아이디의 중복 여부를 확인
		return userRepository.existsByUserId(userId);
	}


	/**
	 *
	 기능 : 회원가입
	 */
	public ResDTO register(UserDTO userDTO) {

		// 1. userDTO로 들어온 아이디가 비어있을 경우
		if(userDTO.getUserId().equals("")) {
			return ResDTO.builder()
					.code("402")
					.message("비어있는 아이디")
					.data(false)
					.build();
		}

		// 2. 비밀번호 일치 여부
		if(!userDTO.getPassword().equals(userDTO.getPassword2())) {
			return ResDTO.builder()
					.code("401")
					.message("패스워드 불일치")
					.data(false)
					.build();
		}

		// 3. DTO -> Entity로 변환
		UserEntity userEntity = UserMapper.instance.userDTOToUserEntity(userDTO);

		// 4. 아이디 중복인 경우
		// userDTO로 들어온 아이디를 userEntity에 있는 아이디와 비교
		if(userRepository.existsByUserId(userEntity.getUserId())){
			// true -> 중복
			return ResDTO.builder()
					.code("400")
					.message("중복된 아이디")
					.data(false)
					.build();
		}

		// 5. 비밀번호 암호화
		userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		// 6. 사용자에게 UserRole 객체를 생성하고 이를 UserEntity의 역할 목록에 추가한 후 저장
		// Role 객체 생성 또는 가져오기
		Role role = new Role();
		role.setId(1L);
		role.setRoleName("ROLE_USER"); // 역할 이름 설정

		// UserRole 객체 생성
		UserRole userRole = new UserRole();
		userRole.setRoleId(1L); // 새로 생성한 UserRole 객체의 role_id를 설정
		userRole.setRoles(role); // UserRole 객체에 Role 객체 설정

		// UserEntity에 UserRole 객체를 설정
		List<UserRole> roleList = new ArrayList<>();
		roleList.add(userRole);
		userEntity.setRoleList(roleList);

		// 7. 회원가입 성공
		return ResDTO.builder()
				.code("200")
				.message("회원가입 성공")
				.data(userRepository.save(userEntity))
				.build();
	}


	/**
	 *
	 기능 : 로그인
	 */
	public JwtToken login(String userId, String password) {
		try{
			// 1. Login ID/PW 를 기반으로 Authentication 객체 생성
			// 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);

			// 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
			// authenticate 매서드가 실행될 때 UserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
			Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

			// 3. 인증 정보를 기반으로 JWT 토큰 생성
			// 이 토큰에는 사용자의 정보와 권한 등이 포함됨
			JwtToken tokenInfo = jwtTokenProvider.generateToken(authentication);

			// REDIS에 Refresh Token 저장
			String key = UUID.randomUUID().toString();
		//	stringRedisTemplate.opsForValue().set(key, tokenInfo.getRefreshToken());
			// Refresh Token을 Redis에 저장할 때 사용자 이름을 키로 사용
			 stringRedisTemplate.opsForValue().set(authentication.getName(), tokenInfo.getRefreshToken());
			// 생성된 키를 사용자 이름과 매핑하여 별도의 Redis 키-값 쌍으로 저장
			// stringRedisTemplate.opsForValue().set(authentication.getName(), key);

			// "refreshToken"이라는 이름의 쿠키를 생성
			Cookie cookie = new Cookie("refreshToken", tokenInfo.getRefreshToken());

			// 쿠키 만료 시간 설정 expires in 7 days
			cookie.setMaxAge(7 * 24 * 60 * 60);

			// optional properties
			cookie.setSecure(true);    // Secure 속성 설정: 쿠키가 HTTPS 연결에서만 전송되도록 설정
			cookie.setHttpOnly(true);   // HttpOnly 속성 설정: JavaScript로 쿠키에 접근할 수 없도록 설정 (XSS 공격에 대응)
			cookie.setPath("/");   // 쿠키의 경로 설정

			// add cookie to response 생성한 쿠키를 응답에 추가하여 클라이언트로 전송
			response.addCookie(cookie);

			// TODO: 필요한지 확인
			//  토큰 정보 초기화
			//tokenInfo.setRefreshToken(null);

			return tokenInfo;
		} catch (BadCredentialsException e) {
			// 비밀번호가 틀렸을 때 발생하는 예외
			return null;
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}


	/**
	 *
	 기능 : 카카오 로그인
	 */
	// 3. userInfo 맵을 입력으로 받아 JWT 토큰을 반환하는 메서드
	public JwtToken kakaoLogin(Map<String, Object> userInfo) {
		// userInfo 맵에서 Kakao ID를 가져와 문자열로 변환
		String kakaoId = userInfo.get("id").toString();

		// kakaoId를 사용하여 데이터베이스에서 사용자를 조회
		UserEntity user = userRepository.findByKakaoId(kakaoId)
				// 만약 사용자가 존재하지 않으면 -> 새로운 사용자를 생성
				.orElseGet(() -> {
					UserEntity newUser = new UserEntity();
					newUser.setKakaoId(kakaoId);
					newUser.setUserId(kakaoId); // KakaoID를 userId로 설정

					// 사용자에게 UserRole 객체를 생성하고 이를 UserEntity의 역할 목록에 추가한 후 저장
					List<UserRole> roleList = new ArrayList<>();   // UserRole 객체들을 저장할 ArrayList 생성
					UserRole userRole = new UserRole();   // 새로운 UserRole 객체 생성
					userRole.setRoleId(1L);   // 새로 생성한 UserRole 객체의 role_id를 설정
					roleList.add(userRole);   // 생성한 UserRole 객체를 roleList에 추가
					newUser.setRoleList(roleList);   // UserEntity에 roleList를 설정

					return userRepository.save(newUser);
				});

			// 사용자 정보로부터 Spring Security의 UserDetails 객체 생성
			UserDetails userDetails = createUserDetails(user);

			// UserDetails 객체를 사용하여 Authentication 객체 생성
			Authentication authentication = createAuthentication(userDetails);

			// JWT 토큰 생성
			return jwtTokenProvider.generateToken(authentication);
		}


	/**
	 *
	 기능 : 네이버 로그인
	 */
	public JwtToken naverLogin(Map<String, Object> userInfo) {
		// userInfo 맵에서 Kakao ID를 가져와 문자열로 변환
		String naverId = userInfo.get("id").toString();

		// kakaoId를 사용하여 데이터베이스에서 사용자를 조회
		UserEntity user = userRepository.findByKakaoId(naverId)
				// 만약 사용자가 존재하지 않으면 -> 새로운 사용자를 생성
				.orElseGet(() -> {
					UserEntity newUser = new UserEntity();
					newUser.setKakaoId(naverId);
					newUser.setUserId(naverId); // KakaoID를 userId로 설정

					// 사용자에게 UserRole 객체를 생성하고 이를 UserEntity의 역할 목록에 추가한 후 저장
					List<UserRole> roleList = new ArrayList<>();   // UserRole 객체들을 저장할 ArrayList 생성
					UserRole userRole = new UserRole();   // 새로운 UserRole 객체 생성
					userRole.setRoleId(1L);   // 새로 생성한 UserRole 객체의 role_id를 설정
					roleList.add(userRole);   // 생성한 UserRole 객체를 roleList에 추가
					newUser.setRoleList(roleList);   // UserEntity에 roleList를 설정

					return userRepository.save(newUser);
				});

		// 사용자 정보로부터 Spring Security의 UserDetails 객체 생성
		UserDetails userDetails = createUserDetails(user);

		// UserDetails 객체를 사용하여 Authentication 객체 생성
		Authentication authentication = createAuthentication(userDetails);

		// JWT 토큰 생성
		return jwtTokenProvider.generateToken(authentication);
	}



	/**
	 *
	 기능 : UserEntity 정보를 바탕으로 UserDetails 객체 생성
	 */
	private UserDetails createUserDetails(UserEntity user) {
//		return new User(user.getUserId(), "", Collections.emptyList());
		List<GrantedAuthority> authorities = user.getRoleList().stream()
				.map(userRole -> new SimpleGrantedAuthority(userRole.getRoles().getRoleName()))
				.collect(Collectors.toList());

		return new User(user.getUserId(), "", authorities);
	}


	/**
	 *
	 기능 : UserDetails 객체를 사용하여 Authentication 객체 생성
	 */
	private Authentication createAuthentication(UserDetails userDetails) {
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}


}