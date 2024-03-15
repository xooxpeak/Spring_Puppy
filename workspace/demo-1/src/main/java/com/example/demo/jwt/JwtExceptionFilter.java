package com.example.demo.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// 토큰 인증시 오류가 발생하는 경우 처리되는 예외처리 코드
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            filterChain.doFilter(request, response); // JwtAuthenticationFilter에서 예외가 발생하면 예외를 던지고 여기서 처리
//        } catch (ApiException | IOException e) {
//            setErrorResponse(request, response, e);
//        }
    }

//    public void setErrorResponse(HttpServletRequest req, HttpServletResponse res, ApiException e) throws IOException {
//        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        final Map<String, Object> body = new HashMap<>();
//        body.put("code", e.getError().getCode());
//        body.put("codeError", e.getError().getErrorCode());
//        body.put("status", e.getError().getStatus());
//        body.put("error", e.getError());
//        body.put("message", e.getMessage());
//        body.put("detail", e.getError().getDetail());
//        body.put("timestamp", e.getError().getLocalDateTime());
//        body.put("path", req.getServletPath());
//
//        final ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(res.getOutputStream(), body);
//        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
//    }

}
