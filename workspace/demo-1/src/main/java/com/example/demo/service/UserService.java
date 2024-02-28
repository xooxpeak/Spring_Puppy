package com.example.demo.service;

import com.example.demo.dto.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;


@Service
public class UserService {
	
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

		// 3. 회원가입 성공
		return ResDTO.builder()
				.code("200")
				.message("회원가입 성공")
				.data(userRepository.save(userEntity))
				.build();
	}
	
}
