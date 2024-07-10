
![댕린이집](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/2a5c1f2b-a063-4778-b458-65f5f7802b81)

# 댕린이집 : 반려견유치원 기록 & 커뮤니티 사이트

반려견 천만 시대에 살고있지만, 대부분의 반려견 유치원 시설이 아직도 수기 기록을 사용하는 현실을 보고 더 나은 웹 서비스를 제공하고자 제작된 사이트 입니다.<br>
반려견 유치원의 훈련사와 보호자가 함께 사용할 수 있는 알림장 및 커뮤니티 플랫폼으로 훈련사는 유치원을 등록하고 운영할 수 있으며, 보호자는 원하는 유치원에 반려견을 등록할 수 있습니다. <br>
웹사이트 하나로 내 반려견의 유치원 생활을 모두 기록하고 추억할 수 있으며, 다른 사람들과 공유하고 소통할 수 있는 서비스를 지원합니다.

<br>
<br>

# 🥇 프로젝트의 주요 목표
1. 이제까지 학습한 java+spring과 새롭게 학습한 기술 스택들을 종합적으로 사용하여 구현
2. 기획부터 구현, 배포까지 전반적인 웹 개발의 과정 학습

<br>
<br>

# 개발 인원 (1명)
김수진 - 개인 프로젝트

<br>
<br>

# 사용 기술 및 개발환경
![image](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/27ebf86a-9ade-4b55-9cab-a409e9fc6486)

Java 17
Spring Boot 3.2.1 <br>
Spring Data JPA <br>
Spring Security <br>
Gradle <br>
MySQL <br>
React

<br>
<br>

# ERD
![image](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/91ebbeea-552b-401a-bbd8-8f1adce4412d)

<br>
<br>

# API 명세서
https://green-dosa-d72.notion.site/34dc1b5e86624c778e5c55779eeb06a9?v=4840f30dff1f4b5a8fedadb55fffd1bb&pvs=4

<br>
<br>

# 주요 기능 (기능명세서)
https://green-dosa-d72.notion.site/7ad192a5754941b6aa113854e86e9721?pvs=4 
 <br>
- 강아지 등록 <br>
- 알림장 <br>
    - 알림장 생성/수정/삭제
    - 알림장 조회
- 사진첩
    - 이미지 업로드/삭제
    - 사진첩 조회
- 게시판
    - 게시글 생성/수정/삭제
    - 게시글 조회
    - 조회수
    - 좋아요
    - 댓글
    - 파일 업로드
- 그 외 기능
    - 카카오 소셜 로그인 (OAuth 2.0 프로토콜)
    - 네이버 소셜 로그인 (OAuth 2.0 프로토콜)
    - 설정 (개인정보 수정) => 구현 예정
    - 1:1 채팅 => 구현 예정

<br>
<br>

# 프로젝트의 주요 관심사
- 실제 서비스 가능한 완성도로 서버-클라이언트 구현(API, UI, 보안, 통신 규약, 기능성)
- Spring Security와 JWT를 사용한 회원 관리 시스템 구현
    - 스프링의 인터셉터를 사용해 로그인이 필요한 접근 시 인터셉터가 JWT 토큰을 검증
    - 보안 기능 향상을 위해 Access Token과 Refresh Token 두 가지 토큰을 사용
    - 비밀번호를 암호화하여 저장하기 위해 Spring Security의 BycryptEncoder 사
- 사용자 편의성 증진을 위한 소셜 로그인 기능 구현
- CRUD 기능 구현

<br>
<br>

# 주요 기능별 화면

| 회원가입 | 로그인 |
| --- | --- |
| ![회원가입](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/adf5ecca-f53d-498d-b4a5-640dae830472) | ![로그인](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/c779ca16-032c-4e98-9dc5-b69f727ed353) |
| 강아지 등록 | 강아지 조회 |
| ![강아지 등록](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/8e782804-2f92-43a7-8541-4d9f72673e28) | ![강아지 조회](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/343ff31b-887a-41db-bc4e-c5351726a528) |
| 알림장 작성 | 알림장 조회 |
| ![알림장 작성](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/5f8bb31a-9110-4c14-8859-5902c83387d6) | ![알림장 조회](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/bd77ab09-e82d-4f24-ad40-815b04279fbf) |
| 사진첩 작성 | 사진첩 조회 |
| ![사진첩 작성](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/5c28a242-ff3c-41ea-b222-0030d7d69723) | ![사진첩 상세보기](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/d99cd895-77f2-41a1-bc4b-2906c0dc9bf4) |
| 게시글 작성 | 게시글 조회 |
| ![게시글 작성](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/ff00b01c-93cc-4bd4-84f4-24c6343577f2) | ![게시글 조회](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/3e96d1d4-7e9d-450f-9443-5750610806a4) |
| 댓글 | 좋아요 |
| ![댓글](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/435cb0f8-8ae5-43aa-b664-af9a9dfcecaa) | ![좋아요](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/f3453dc5-99dd-40d4-a846-0fdec4e510d3) |

<br>
<br>

# 남은 구현들 (구현 예정)
1. 마이페이지
2. 친구 목록
3. 소켓을 통한 1:1 채팅 기능
4. redis를 사용해 Refresh Token 관리
5. 배포
<br>
<br>

# 📝 구현 과정 기록
블로그에서 확인하실 수 있습니다!<br>
https://xooxpeak.tistory.com/category/Project%20%EB%8C%95%EB%A6%B0%EC%9D%B4%EC%A7%91?page=1











  
