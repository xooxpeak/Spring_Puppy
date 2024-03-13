package com.example.demo.service;

import com.example.demo.dto.ResDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.jwt.JwtToken;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    // 로그인 시 실행되는 함수 loadUserByUsername
    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user;
    }


    // 로그인
    public ResDTO login(String email, String password) {
        // 1. email, password 기반으로 Authentication 객체 생성
        // 이때 authentication은 인증 여부를 확인하는 authentication 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 User에 대한 검증 진행
        // authenticate() 메서드가 실행될 때 CustomUserDetailsService에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = JwtTokenProvider.generateToken(authentication);

        return ResDTO.builder()
                .code("200")
                .message("로그인 성공")
                .data(jwtToken)
                .build();
    }

}