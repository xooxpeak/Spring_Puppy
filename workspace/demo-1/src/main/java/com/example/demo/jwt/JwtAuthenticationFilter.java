//package com.example.demo.jwt;
//
//import com.example.demo.service.RedisService;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.ExpiredJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Map;
//
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtTokenProvider jwtTokenProvider;
//    private final RedisService redisService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        // 1. Request Header에서 JWT 토큰 추출
//        String token = resolveToken(request);
//
//        if (token != null) {
//            try {
//                if (jwtTokenProvider.validateToken(token)) {
//                    // 토큰이 유효할 경우, 토큰에서 Authentication 객체를 가져와 SecurityContext에 저장
//                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            } catch (ExpiredJwtException e) {
//                // AccessToken이 만료된 경우
//
//                // 요청 본문에서 RefreshToken을 추출
//                Map<String, String> body = extractRequestBody(request);
//                String refreshToken = body.get("refreshToken");
//
//                if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {
//                    // RefreshToken이 유효한 경우 -> 새로운 AccessToken을 발급받아야 함
//                    JwtToken newToken = redisService.refreshAccessToken(refreshToken);
//                    response.setHeader("AccessToken", newToken.getAccessToken());
//                    response.setHeader("RefreshToken", newToken.getRefreshToken());
//
//                    // 새로운 AccessToken으로 SecurityContext를 업데이트
//                    Authentication authentication = jwtTokenProvider.getAuthentication(newToken.getAccessToken());
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                } else {
//                    // RefreshToken이 유효하지 않은 경우 -> 처리 (로그아웃 혹은 재로그인 필요)
//                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                    response.getWriter().write("Refresh token is invalid or expired");
//                    response.getWriter().flush();
//                    return;
//                }
//            } catch (Exception e) {
//                // 다른 예외 발생 시 401 응답 반환
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
//                return;
//            }
//        }
//
//        // 다음 필터로 요청과 응답을 전달
//        filterChain.doFilter(request, response);
//    }
//
//    private Map<String, String> extractRequestBody(HttpServletRequest request) throws IOException {
//        StringBuilder stringBuilder = new StringBuilder();
//        String line;
//        try (BufferedReader bufferedReader = request.getReader()) {
//            while ((line = bufferedReader.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//        }
//        if (stringBuilder.length() == 0) {
//            return Collections.emptyMap();
//        }
//
//        return new ObjectMapper().readValue(stringBuilder.toString(), new TypeReference<Map<String, String>>(){});
//    }
//
//    // Request Header 에서 토큰 정보 추출하는 메서드
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}

package com.example.demo.jwt;

import com.example.demo.service.RedisService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
        String token = resolveToken(request);

        if (token != null) {
            try {
                if (jwtTokenProvider.validateToken(token)) {
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access token is expired");
                return;
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
