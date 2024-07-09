package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
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


    // TODO: 확인
    public String getAccessToken(String code) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());  // 추가
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));  // 추가

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);



        // 요청 파라미터 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("redirect_uri", redirectUri);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

//        Map<String, String> params = new HashMap<>();
//        params.put("grant_type", "authorization_code");
//        params.put("client_id", clientId);
//        params.put("client_secret", clientSecret);
//        params.put("code", code);
//        params.put("redirect_uri", redirectUri);
////        params.put("state", state);
//
//        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, headers);

//        ResponseEntity<JSONObject> response = restTemplate.exchange(tokenUri, HttpMethod.POST, request, JSONObject.class);
//        if (response.getStatusCode() == HttpStatus.OK) {
//            JSONObject responseBody = response.getBody();
//            return (String) responseBody.get("access_token");
//        } else {
//            throw new RuntimeException("Failed to get access token from Naver");
//        }

        // 추가
        ResponseEntity<Map> response = restTemplate.exchange(tokenUri, HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            return (String) responseBody.get("access_token");
        } else {
            throw new RuntimeException("Failed to get access token from Naver");
        }
    }


    public Map<String, Object> getUserInfo(String accessToken) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
//        headers.set("Authorization", "Bearer " + accessToken);
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<String> request = new HttpEntity<>(headers);

//        ResponseEntity<JSONObject> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, request, JSONObject.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            JSONObject responseBody = response.getBody();
//            return (Map<String, Object>) responseBody.get("response");
//        } else {
//            throw new RuntimeException("Failed to get user info from Naver");
//        }

        // 추가
        ResponseEntity<Map> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            return (Map<String, Object>) responseBody.get("response");
        } else {
            throw new RuntimeException("Failed to get user info from Naver");
        }
    }

}
