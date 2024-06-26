package com.example.demo.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;

    // application.property에서 secret 값 가져와서 key에 저장
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 유저 정보를 가지고 AccessToken, RefreshToken을 생성하는 메서드
    public JwtToken generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // 현재 시간을 milliseconds 단위로 가져오기
        long now = (new Date()).getTime();

        // Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "HS256");
        headers.put("typ", "JWT");

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + 1800000);   // 30분
        String accessToken = Jwts.builder()
                .setHeader(headers)
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setHeader(headers)
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(new Date(now + 86400000))  // 1일
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {

        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()    // JWT를 파싱하고 검증하는 빌더 객체 생성
                    .setSigningKey(key)   // 해당 토큰을 생성할 때 사용된 키를 설정
                    .build()
                    .parseClaimsJws(token);   // 해당 토큰을 파싱하고 서명을 검증
            return true;    // 성공 시 -> 유효한 토큰임 -> true 반환
        } catch (SecurityException | MalformedJwtException e) {   // 잘못된 형식인 경우
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {   // 토큰이 만료된 경우
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {    // 지원되지 않는 토큰인 경우
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    // accessToken이 만료된 경우
//    public JwtToken refreshToken(HttpServletRequest request) {
//        // 쿠키에서 리프레시 토큰을 가져옴
//            String refreshToken = getRefreshTokenFromCookie(request);
//    try {
//        // 리프레시 토큰을 사용하여 새로운 액세스 토큰 발급
//        JwtToken newAccessToken = generateNewAccessToken(refreshToken);

//        // 발급된 새로운 액세스 토큰을 클라이언트에게 반환
//        return newAccessToken;
//    } catch (Exception e) {
//        log.error("Failed to refresh access token", e);
//        throw new ApiException(ExceptionEnum.TOKEN_REFRESH_FAILED);
//    }
//}

    // 리프레시 토큰을 사용하여 새로운 액세스 토큰을 발급하는 메서드
//    private JwtToken generateNewAccessToken(String refreshToken) {
//        try {
//            // 리프레시 토큰을 사용하여 사용자 정보를 가져옴
//            Authentication authentication = getAuthentication(refreshToken);

//            // 새로운 액세스 토큰 생성
//            JwtToken newAccessToken = generateToken(authentication);

//            // 발급된 액세스 토큰을 클라이언트에게 반환
//            return newAccessToken;
//        } catch (Exception e) {
//            log.error("Failed to generate new access token", e);
//            throw new ApiException(ExceptionEnum.TOKEN_GENERATION_FAILED);
//        }
//    }


    // accessToken
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
