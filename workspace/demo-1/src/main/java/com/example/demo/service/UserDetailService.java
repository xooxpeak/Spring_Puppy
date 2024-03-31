package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 사용자 이름을 입력받아 해당 사용자의 정보를 검색하고
    // 이를 Spring Security의 UserDetails 객체로 변환하여 반환
    // loadUserByUsername: 로그인 시 실행되는 함수
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        UserEntity userEntity = userRepository.findByUserId(username);
//        return userEntity;
        return userRepository.findByUserId(userId)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    // 해당하는 User의 데이터가 존재한다면 Spring security의 UserDetails 객체로 만들어 리턴
    private UserDetails createUserDetails(UserEntity userEntity) {
        return UserEntity.builder()
                .userId(userEntity.getUserId())
                .password(passwordEncoder.encode(userEntity.getPassword()))
                .role(List.of(userEntity.getRole().toArray(new String[0])))
                .build();
    }

//    public ResDTO login(String userId, String password, HttpServletResponse response) {
//        // 1. Login ID/PW를 기반으로 Authentication 객체 생성
//        // 이때 authentication은 인증 여부를 확인하는 authenticated 값이 false
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);
//
//        // 2. 실제 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
//        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
//        Authentication authentication = AuthenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
//        // 3. 인증 정보를 기반으로 JWT 토큰 생성
//        JwtToken tokenInfo = JwtTokenProvider.generate
//
//        // 쿠키
//        Cookie cookie = new Cookie("refreshToken", tokenInfo.getRefreshToken());
//
//        // expires in 7 days
//        cookie.setMaxAge(7 * 24 * 60 * 60);
//
//        // optional properties
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//
//        // add cookie to response
//        response.addCookie(cookie);
//
//        tokenInfo.setRefreshToken(null);
//
//        return ResDTO.builder()
//                .message("로그인 성공")
//                .data(tokenInfo)
//                .build();
//
//    }
}
