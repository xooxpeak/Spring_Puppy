package com.example.demo.service;

import com.example.demo.jwt.JwtToken;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RedisService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     기능 : Redis에서 저장된 리프레시 토큰을 검증하고 새로운 액세스 토큰을 생성
     */
    // 사용자 정보를 추출하고 Redis에 저장된 refreshToken을 가져와 비교
    public JwtToken refreshAccessToken(String refreshToken) {

        // 사용자 정보를 Refresh Token에서 추출
        Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);

        // Redis에서 사용자 이름을 키로 하여 저장된 Refresh Token을 조회
        String redisRefreshToken = stringRedisTemplate.opsForValue().get(authentication.getName());

        // 사용자 이름을 키로 하여 저장된 임의의 키를 조회
        // String key = stringRedisTemplate.opsForValue().get(authentication.getName());
        // Redis에서 임의의 키를 사용하여 저장된 Refresh Token을 조회
        // String redisRefreshToken = stringRedisTemplate.opsForValue().get(key);

        // Redis에서 저장된 Refresh Token과 클라이언트가 보낸 Refresh Token 비교하여 일치 여부 확인
        if (redisRefreshToken != null && redisRefreshToken.equals(refreshToken)) {

            // 일치한다면 새로운 AccessToken 발급
            JwtToken newAccessToken = jwtTokenProvider.generateToken(authentication);
            System.out.println("Valid refresh token.");

            // TODO: 새로운 Refresh Token 생성 및 Redis에 저장 ?
            // JwtToken newRefreshToken = jwtTokenProvider.generateToken(authentication);
            // stringRedisTemplate.opsForValue().set(authentication.getName(), String.valueOf(newRefreshToken));

            // 새로운 AccessToken과 RefreshToken을 반환
            return JwtToken.builder()
                    .grantType("Bearer")
                    .accessToken(String.valueOf(newAccessToken))
            //        .accessToken(newAccessToken.getAccessToken())
                    .refreshToken(refreshToken)
            //        .refreshToken(newRefreshToken.getRefresshToken())
                    .build();
        }

        // 일치하지 않는다면 유효하지 않은 토큰
        System.out.println("Invalid refresh token.");
        System.out.println("User refreshToken: " + refreshToken);
        System.out.println("Redis refreshToken for user " + authentication.getName() + ": " + redisRefreshToken);
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        // return null;

    }


}
