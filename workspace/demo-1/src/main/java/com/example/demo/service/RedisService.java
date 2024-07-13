package com.example.demo.service;

import com.example.demo.jwt.JwtToken;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class RedisService {

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final RedisRepository redisRepository;
    @Autowired
    private final StringRedisTemplate stringRedisTemplate;


//    private JwtTokenProvider jwtTokenProvider;
//
//    private UserService userService;
//
//    private RedisRepository redisRepository;
//
//    private StringRedisTemplate stringRedisTemplate;

    /**
     기능 : Redis에서 저장된 리프레시 토큰을 검증하고 새로운 액세스 토큰을 생성
     */
    // 사용자 정보를 추출하고 Redis에 저장된 refreshToken을 가져와 비교
//    public JwtToken refreshAccessToken(String refreshToken) {
//
//        // 사용자 정보를 Refresh Token에서 추출
//        Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
//
//        // Redis에서 사용자 이름을 키로 하여 저장된 Refresh Token을 조회
//        String redisRefreshToken = stringRedisTemplate.opsForValue().get(authentication.getName());
//
//        // Redis에서 저장된 Refresh Token과 클라이언트가 보낸 Refresh Token 비교하여 일치 여부 확인
//        if (redisRefreshToken != null && redisRefreshToken.equals(refreshToken)) {
//
//            // 일치한다면 새로운 AccessToken 발급
//            JwtToken newAccessToken = jwtTokenProvider.generateToken(authentication);
//            System.out.println("Valid refresh token.");
//
//            // 새로운 Refresh Token 생성 및 Redis에 저장
//            JwtToken newRefreshToken = jwtTokenProvider.generateToken(authentication);
//            stringRedisTemplate.opsForValue().set(authentication.getName(), String.valueOf(newRefreshToken));
//
//            // 새로운 AccessToken과 RefreshToken을 반환
//            return JwtToken.builder()
//                    .grantType("Bearer")
////                    .accessToken(String.valueOf(newAccessToken))
//                    .accessToken(newAccessToken.getAccessToken())
//                    //       .refreshToken(refreshToken)
//                    .refreshToken(newRefreshToken.getRefreshToken())
//                    .build();
//        }
//
//        // 일치하지 않는다면 유효하지 않은 토큰
//        System.out.println("Invalid refresh token.");
//        System.out.println("User refreshToken: " + refreshToken);
//        System.out.println("Redis refreshToken" + authentication.getName() + ": " + redisRefreshToken);
//        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
//        // return null;
//
//    }

    public void storeRefreshToken(String userId, String refreshToken) {
        stringRedisTemplate.opsForValue().set(userId, refreshToken);
    }

    public JwtToken refreshAccessToken(String accessToken) {
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        String userName = authentication.getName();
        String redisRefreshToken = stringRedisTemplate.opsForValue().get(userName);

        if (redisRefreshToken != null) {
            JwtToken newToken = jwtTokenProvider.generateToken(authentication);

            // 새로운 리프레시 토큰도 함께 생성하여 저장
            storeRefreshToken(userName, newToken.getRefreshToken());

            return JwtToken.builder()
                    .grantType("Bearer")
                    .accessToken(newToken.getAccessToken())
                    .refreshToken(null)  // 클라이언트에는 리프레시 토큰을 반환하지 않음
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }
    }


}