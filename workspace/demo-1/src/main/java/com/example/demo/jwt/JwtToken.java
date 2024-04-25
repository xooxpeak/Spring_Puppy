package com.example.demo.jwt;

import lombok.*;

@Builder
@Data
@Getter @Setter
@AllArgsConstructor
public class JwtToken {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
