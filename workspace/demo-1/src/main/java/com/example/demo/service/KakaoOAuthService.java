package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

// 1. Kakao OAuth 서비스와 통신하여 access token을 얻는 역할을 하는 서비스 클래스
@Service
public class KakaoOAuthService {

    // application.properties에서 kakao.client-id 값을 가져와 clientId 변수에 주입
    @Value("${kakao.client-id}")
    private String clientId;  // 클라이언트 ID를 저장

    // application.properties에서 kakao.redirect-uri 값을 가져와 redirectUri 변수에 주입
    @Value("${kakao.redirect-uri}")
    private String redirectUri;  // 리다이렉트 URI를 저장

    // application.properties에서 kakao.client-secret 값을 가져와 clientSecret 변수에 주입
    @Value("${kakao.client-secret}")
    private String clientSecret;


    // code를 입력으로 받아 access token을 반환하는 메소드
    public String getAccessToken(String code) {

        // Kakao OAuth 토큰 요청 URL을 정의
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        // HTTP 요청을 보내기 위해 Spring의 RestTemplate 객체를 생성
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 요청에 사용할 헤더를 생성
        HttpHeaders headers = new HttpHeaders();
        // Content-Type 헤더를 설정. 여기서는 URL 인코딩된 폼 데이터를 사용
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        // 주어진 URL을 사용하여 URI 빌더를 생성
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(tokenUrl)
                // grant_type 파라미터를 추가. 여기서는 authorization_code를 사용
                .queryParam("grant_type", "authorization_code")
                // client_id 파라미터를 추가하고 clientId 값을 사용
                .queryParam("client_id", clientId)
                // redirect_uri 파라미터를 추가하고 redirectUri 값을 사용
                .queryParam("redirect_uri", redirectUri)
                // code 파라미터를 추가하고 메소드의 매개변수 code 값을 사용
                .queryParam("code", code)
                // client_secret 파라미터를 추가하고 clientSecret 값을 사용
                .queryParam("client_secret", clientSecret);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", clientSecret);

        // 헤더를 포함한 HTTP 엔티티를 생성
        HttpEntity<?> entity = new HttpEntity<>(headers);

        // HTTP 요청을 보내고 응답을 받아옴
        ResponseEntity<Map> response = restTemplate.exchange(
                builder.toUriString(),  // 완성된 URI 문자열을 가져옴
                HttpMethod.POST,  // HTTP 메소드는 POST를 사용
                entity,  // 요청 엔티티를 사용
                Map.class  // 응답 바디를 맵 형태로 변환
        );


        // 응답 상태 코드가 HTTP 200 OK인지 확인
        if (response.getStatusCode() == HttpStatus.OK) {
            // 응답 바디를 맵 형태로 가져
            Map<String, Object> responseBody = response.getBody();
            // 응답 바디에서 access_token 값을 가져와 문자열로 반환
            return (String) responseBody.get("access_token");
        } else {
            throw new RuntimeException("kakao로부터 token 가져오기 실패");
        }
    }


    // 2. accessToken을 입력으로 받아 사용자 정보를 포함하는 맵을 반환
    public Map<String, Object> getUserInfo(String accessToken) {

        // 사용자 정보 요청을 위한 Kakao API URL을 정의
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";

        // HTTP 요청을 보내기 위해 Spring의 RestTemplate 객체를 생성
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 요청에 사용할 헤더를 생성
        HttpHeaders headers = new HttpHeaders();
        // Authorization 헤더를 설정. Bearer 토큰 방식으로 accessToken을 사용
    //    headers.set("Authorization", "Bearer " + accessToken);
        headers.setBearerAuth(accessToken);

        // 헤더를 포함한 HTTP 엔티티를 생성
        HttpEntity<?> entity = new HttpEntity<>(headers);

        // HTTP 요청을 보내고 응답을 받아옴
        ResponseEntity<Map> response = restTemplate.exchange(
                userInfoUrl,  // 사용자 정보 요청 URL을 사용
                HttpMethod.GET,  // HTTP 메소드는 GET을 사용
                entity,  // 요청 엔티티를 사용
                Map.class  // 응답 바디를 맵 형태로 변환
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            // 응답 바디를 반환. 여기에는 사용자 정보가 포함되어 있음.
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to get user info from Kakao");
        }
    }

}
