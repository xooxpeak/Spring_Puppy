
![댕린이집](https://github.com/xooxpeak/Spring_Puppy/assets/136714432/2a5c1f2b-a063-4778-b458-65f5f7802b81)

# 댕린이집 : 반려견유치원 기록 & 커뮤니티 사이트

반려견 천만 시대에 살고있지만, 대부분의 반려견 유치원 시설이 아직도 수기 기록을 사용하는 현실을 보고 더 나은 웹 서비스를 제공하고자 제작된 사이트 입니다.<br>
반려견 유치원의 훈련사와 보호자가 함께 사용할 수 있는 알림장 및 커뮤니티 플랫폼으로 훈련사는 유치원을 등록하고 운영할 수 있으며, 보호자는 원하는 유치원에 반려견을 등록할 수 있습니다. <br>
웹사이트 하나로 내 반려견의 유치원 생활을 모두 기록하고 추억할 수 있으며, 다른 사람들과 공유하고 소통할 수 있는 서비스를 지원합니다.
<br>
<br>

# 개발 인원 (1명)
김수진 - 개인 프로젝트
<br>
<br>

# 사용 기술
Spring Boot <br>
Spring Data JPA <br>
Gradle
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
    - 설정 (개인정보 수정)
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
# 기능별 화면
1. 회원가입 <br>





  
