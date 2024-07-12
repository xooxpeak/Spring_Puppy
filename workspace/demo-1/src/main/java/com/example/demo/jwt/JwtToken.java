package com.example.demo.jwt;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "jwtToken", timeToLive = 60)
public class JwtToken {
    private String grantType;
    private String accessToken;

    private String refreshToken;
}
