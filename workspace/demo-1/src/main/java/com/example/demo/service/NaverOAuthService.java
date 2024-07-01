package com.example.demo.service;

import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class NaverOAuthService {

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.provider.naver.token-uri}")
    private String tokenUri;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String userInfoUri;

    public String getClientId() {
        return clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();

    // state 값을 생성하여 반환
//    public String generateState(HttpSession session) {
//        String state = UUID.randomUUID().toString();
//        session.setAttribute("oauth_state", state);
//        return state;
//    }

    // state 값을 검증
    public boolean validateState(HttpSession session, String state) {
        String savedState = (String) session.getAttribute("oauth_state");
        return savedState != null && savedState.equals(state);
    }

    public String getAccessToken(String code) {

        // 네이버 OAuth 토큰 요청 URL을 정의
        //String tokenUrl = "https://nid.naver.com/oauth2.0/token";

        // state 값 검증
//        if (!validateState(session, state)) {
//            throw new RuntimeException("Invalid state parameter");
//        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 요청 파라미터 설정
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", clientId);
//        params.add("client_secret", clientSecret);
//        params.add("code", code);
//        params.add("redirect_uri", redirectUri);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("code", code);
        params.put("redirect_uri", redirectUri);
//        params.put("state", state);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<JSONObject> response = restTemplate.exchange(tokenUri, HttpMethod.POST, request, JSONObject.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject responseBody = response.getBody();
            return (String) responseBody.get("access_token");
        } else {
            throw new RuntimeException("Failed to get access token from Naver");
        }
    }


    public Map<String, Object> getUserInfo(String accessToken) {

        // 네이버 사용자 정보 요청 URL을 정의
        //String userInfoUrl = "https://openapi.naver.com/v1/nid/me";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
//        headers.set("Authorization", "Bearer " + accessToken);
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<JSONObject> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, request, JSONObject.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject responseBody = response.getBody();
            return (Map<String, Object>) responseBody.get("response");
        } else {
            throw new RuntimeException("Failed to get user info from Naver");
        }
    }

}
