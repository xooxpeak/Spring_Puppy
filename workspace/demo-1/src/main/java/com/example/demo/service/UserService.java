package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

import net.bytebuddy.implementation.bytecode.Throw;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	// 아이디 중복 검사
	public Object idCheck(String userId) throws Exception {
		// TODO Auto-generated method stub
		// 사용자 아이디가 빈 문자열인 경우 예외를 던짐
		if(userId.equals("")) {
			throw new Exception(); 
		}
		// UserRepository를 사용하여 주어진 사용자 아이디의 중복 여부를 확인
		return userRepository.existsByUserId(userId);
	}
	
}
