package com.example.demo.service;

import com.example.demo.dto.ResDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserRole;
import com.example.demo.jwt.JwtToken;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	@Autowired
	AuthenticationManagerBuilder authenticationManagerBuilder;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// 아이디 중복 검사 버튼
	public Object idCheck(String userId) throws Exception {
		// 사용자 아이디가 빈 문자열인 경우 예외를 던짐
		if(userId.equals("")) {
			throw new Exception();
		}
		// UserRepository를 사용하여 주어진 사용자 아이디의 중복 여부를 확인
		return userRepository.existsByUserId(userId);
	}

	// 비밀번호 일치 여부 확인
	/* private ResDTO checkPassword(String password, String passwordCheck) {
		if (!password.equals(passwordCheck)) {
			return ResDTO.builder()
					.code("400")
					.message("패스워드 불일치")
					.data(false)
					.build();
		}
        else return null;
    } */

	// 회원가입
	public ResDTO register(UserDTO userDTO) {
		// 1. userDTO로 들어온 아이디가 비어있을 경우
		if(userDTO.getUserId().equals("")) {
			return ResDTO.builder()
					.code("401")
					.message("비어있는 아이디")
					.data(false)
					.build();
		}

		// 비밀번호 일치 여부
		if(!userDTO.getPassword().equals(userDTO.getPassword2())) {
			return ResDTO.builder()
					.code("401")
					.message("패스워드 불일치")
					.data(false)
					.build();
		}

		// userDTO -> userEntity 로 변형하는 맵핑이 필요함.
		// mapper mapstruct
		UserEntity userEntity = UserMapper.instance.userDTOToUserEntity(userDTO);

		// 2. 아이디 중복인 경우
		// userDTO로 들어온 아이디를 userEntity에 있는 아이디와 비교
		if(userRepository.existsByUserId(userEntity.getUserId())){
			// true -> 중복
			return ResDTO.builder()
					.code("400")
					.message("중복된 아이디")
					.data(false)
					.build();
		}

		// 비밀번호 암호화
		userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		// 사용자에게 UserRole 객체를 생성하고 이를 UserEntity의 역할 목록에 추가한 후 저장
		List<UserRole> roleList = new ArrayList<>();   // UserRole 객체들을 저장할 ArrayList 생성
		UserRole userRole = new UserRole();   // 새로운 UserRole 객체 생성
		userRole.setRoleId(1L);   // 새로 생성한 UserRole 객체의 role_id를 설정
		roleList.add(userRole);   // 생성한 UserRole 객체를 roleList에 추가
		userEntity.setRoleList(roleList);   // UserEntity에 roleList를 설정
		userRepository.save(userEntity);   // 변경된 UserEntity를 UserRepository를 통해 저장

		// 3. 회원가입 성공
		return ResDTO.builder()
				.code("200")
				.message("회원가입 성공")
				.data(userRepository.save(userEntity))
				.build();
	}

	// 로그인
	public JwtToken login(String userId, String password) {
		try{
			// 1. Login ID/PW 를 기반으로 Authentication 객체 생성
			// 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);

			// 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
			// authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
			Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

			// 3. 인증 정보를 기반으로 JWT 토큰 생성
			JwtToken tokenInfo = jwtTokenProvider.generateToken(authentication);

			return tokenInfo; }
		catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}


}
