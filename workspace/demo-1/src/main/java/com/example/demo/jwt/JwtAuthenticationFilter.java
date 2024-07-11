package com.example.demo.jwt;

import com.example.demo.service.RedisService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Request Header에서 JWT 토큰 추출
        String token = resolveToken(request);

        // 2. validateToken 메서드로 토큰 유효성 검사
        // 원래 코드
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            // 토큰이 유효할 경우, 토큰에서 Authentication 객체를 가져와 SecurityContext에 저장
//            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }

        // 변경된 코드
        if (token != null) {
            try {
                if (jwtTokenProvider.validateToken(token)) {
                    // 토큰이 유효할 경우, 토큰에서 Authentication 객체를 가져와 SecurityContext에 저장
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                // AccessToken이 만료된 경우
                String refreshToken = request.getHeader("RefreshToken");
                if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {
                    // RefreshToken이 유효한 경우 -> 새로운 AccessToken을 발급받아야 함
                    JwtToken newToken = redisService.refreshAccessToken(refreshToken);
                    response.setHeader("AccessToken", newToken.getAccessToken());
                    response.setHeader("RefreshToken", newToken.getRefreshToken());

                    // 새로운 AccessToken으로 SecurityContext를 업데이트
                    Authentication authentication = jwtTokenProvider.getAuthentication(newToken.getAccessToken());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    // RefreshToken이 유효하지 않은 경우 -> 처리 (로그아웃 혹은 재로그인 필요)
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return;
                }
            }
        }




        // TODO: 추가
//        else if (token != null && !jwtTokenProvider.validateToken(token)) {
//            String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
//            if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {
//                String newAccessToken = jwtTokenProvider.refreshToken(refreshToken);
//                response.setHeader("Authorization", "Bearer " + newAccessToken);
//                Authentication authentication = jwtTokenProvider.getAuthentication(newAccessToken);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is expired and not valid anymore");
//                return;
//            }
//        }

        // 다음 필터로 요청과 응답을 전달
        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보 추출하는 메서드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
