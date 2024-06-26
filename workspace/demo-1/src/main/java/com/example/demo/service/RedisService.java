package com.example.demo.service;

import com.example.demo.jwt.JwtToken;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class RedisService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private  RedisRepository redisRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     기능 : Redis에서 저장된 리프레시 토큰을 검증하고 새로운 액세스 토큰을 생성
     */
    // 사용자 정보를 추출하고 Redis에 저장된 refreshToken을 가져와 비교
    public JwtToken refreshAccessToken(String refreshToken) {
        
        // 사용자 정보를 Refresh Token에서 추출
        Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);

        // Redis에서 저장된 Refresh Token과 클라이언트가 보낸 Refresh Token 비교
        String storedRefreshToken = stringRedisTemplate.opsForValue().get(authentication.getName());

        // Token 일치 여부 확인
        if (storedRefreshToken != null && storedRefreshToken.equals(refreshToken)) {

            // Access Token 생성
        //    Date accessTokenExpiresIn = new Date(now + 1800000);
        //    String accessToken = Jwts.builder()
        //            .setHeader(headers)
        //            .setSubject(authentication.getName())
        //            .claim("auth", authorities)
        //            .setExpiration(accessTokenExpiresIn)
        //            .signWith(key, SignatureAlgorithm.HS256)
        //            .compact();

            // 새로운 AccessToken 발급
            JwtToken newAccessToken = jwtTokenProvider.generateToken(authentication);

            // 새로운 AccessToken과 RefreshToken을 반환
            return JwtToken.builder()
                    .grantType("Bearer")
                    .accessToken(String.valueOf(newAccessToken))
            //        .accessToken(newAccessToken.getAccessToken())
                    .refreshToken(refreshToken)
                    .build();
        }
        return null;

    }


}
