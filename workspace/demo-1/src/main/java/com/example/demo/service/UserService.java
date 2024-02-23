package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	// 아이디 중복 검사
	public Object idCheck(String userId) throws Exception {
		// 사용자 아이디가 빈 문자열인 경우 예외를 던짐
		if(userId.equals("")) {
			throw new Exception(); 
		}
		// UserRepository를 사용하여 주어진 사용자 아이디의 중복 여부를 확인
		return userRepository.existsByUserId(userId);
	}
	
	// 회원가입
	public Object register(UserDTO userDTO) {
		// userDTO -> userEntity 로 변형하는 맵핑이 필요함.
		// mapper mapstruct
		UserEntity userEntity = UserMapper.instance.userEntityToUserDTO(userDTO);
		return userRepository.save(userEntity);
	}
	
}
