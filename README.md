# 🐾 Petshield - 반려동물 건강 지킴이 (백엔드)

**Petshield**는 반려견의 건강 상태를 기록하고 분석하여, 보호자가 보다 체계적으로 건강을 관리할 수 있도록 도와주는 서비스입니다.  
본 저장소는 Petshield의 **백엔드 API 서버** 레포지토리입니다.

---

## 📌 주요 기능

### 🐶 강아지 정보 관리
- 반려견 기본 정보 입력 및 수정
- 프로필 기반 홈 화면 구성

### 🖼 비만도 검사
- 비만도 이미지 등록
- 등록된 이미지 기반 비만도 판별 및 분석
- 비만도 이미지 불러오기 기능 제공

### 🩺 건강 기록 및 센서 연동
- 반려견의 심박수 및 활동 데이터 기록
- 아두이노와 연동된 실시간 데이터 수신 처리
- 건강 기록을 주간 그래프로 시각화

### 🍖 사료 추천
- 전체 사료 목록 제공
- 사용자의 입력 조건에 따른 개인 맞춤형 사료 추천

### ☁️ 이미지 저장소 관리
- AWS S3를 활용한 비만도 이미지 저장 및 조회
- S3 경로 기반 이미지 접근 및 관리 기능

---

## 🛠️ 기술 스택

- **Framework**: Spring Boot 3.3
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **Cloud**: AWS EC2, S3
- **CI/CD**: GitHub Actions + AWS Elastic BeanStalk
- **API Docs**: Swagger (springdoc-openapi)
- **External Device**: 아두이노 심박수 센서

---

## ⚙️ 로컬 실행 방법

1. 프로젝트 클론

```bash
git clone https://github.com/PetShield2024/BackEnd.git
cd BackEnd
```

2. application-secret.yml 파일 생성 후 다음 항목 설정
```yaml
spring:
  datasource:
    url: ${AWS_DB_URL}
    username: ${AWS_DB_USER}
    password: ${AWS_DB_PASS}
    driver-class-name: com.mysql.cj.jdbc.Driver

... [생략]

cloud:
  aws:
    s3:
      bucket: petshield-dev
      path:
        dog : dogs
        obesity : obesity
    region:
      static: ap-northeast-2
    stack:
      auto: false
    credentials:
      accessKey: ${AWS_ACCESS_KEY_ID}
      secretKey: ${AWS_SECRET_ACCESS_KEY}
server:
  port: 8080  # 기본 HTTP 포트로 설정
```

3. 실행

```bash
./gradlew bootRun
```

🧪 API 문서
Swagger UI: http://localhost:8080/swagger-ui/index.html

🙋 기여자
|이름	|역할|
|--|--|
|[<img src="https://avatars.githubusercontent.com/bigtr3" alt="" style="width:100px;100px;">](https://github.com/bigtr3) <br/><div align="center">전승은</div>|	백엔드 API 개발, DB 설계, 아두이노 연동, 비만도 분석 처리, CI/CD 구축|


