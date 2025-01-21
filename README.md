# 인증서버

### 인증 서버의 역할

- 사용자 자격 증명
- OTP를 생성해서 DB에 저장

### 인증서버 시퀀스 다이어그램

![auth-server-diagram](https://github.com/user-attachments/assets/e7bca09d-0fdd-4f38-9872-973e04d697e8)

### 구현해야 하는 엔드포인트

1. `POST /api/v1/users`: 사용자 추가
2. `POST /api/v1/users/auth`: 사용자 인증, 인증 성공시 OTP 리턴
3. `POST /api/v1/otp/check`: 특정 사용자에게 발급된 OTP가 유효한지 확인

### 인증 서버 클래스 설계

- 일반적인 Controller-Service-Repository 흐름으로 구현

### DB table 설계

사용자 ID/PW를 저장하고 OTP를 관리하기 위한 테이블 필요
- 사용자 ID/PW를 저장하는 User 테이블
- 사용자 별 OTP 값을 관리하는 Otp 테이블
- one to one 관계
- user_id로 연결
