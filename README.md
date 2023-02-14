#패스트캠퍼스교육 스터디활동
swagger- http://localhost:8080/swagger-ui/#/

# 공공데이터 사이트
https://data.gg.go.kr/portal/data/service/selectServicePage.do?page=1&rows=10&sortColumn=&sortDirection=&infId=LU6OWRJ132QKLU6TASZX31096522&infSeq=3&order=&loc=&searchWord=%EC%88%99%EB%B0%95
# 게시판 프로젝트
# 프로젝트 기술
<hr>

SpringBoot 2.7.5 <br>
JDK 17 <br>
MYSQL 8이상 <br>
Redis ==> Docker compose 사용할예정 <br>
Sprnig Security(SecurityContextHolder) <br>
Spring Batch <br>
Spring Data JPA <br>
Thymeleaf(MVC) <br>
## 동작 방식
![image](https://user-images.githubusercontent.com/79129475/218039449-e2fb805e-87bd-44a0-ae53-2aa043845bdd.png)

8080 서버에서 MVC로 화면 그리고 각 기능들을 가지고 있음 --> Spring MVC
8090 서버는 배치 프로그램과 호텔 예약 저장 기능과 삭제 기능만 만들예정 --> RESTAPI



## ERD 
![image](https://user-images.githubusercontent.com/79129475/215671900-322c1cdc-c431-48fc-bdef-febdfc18344a.png)

## 작성법
브랜치 이름 ->  FEAT: 브랜치이름(소문자)  브랜치 이름은 기능명
 혼자 Merge 절대 금지 *****
 메소드 설명 적어주기(주석으로)
 절대 복붙 금지(중요)
 
## 할 일 (하다가 생기면 적을 예정)
 

## 고예림

- 메인 페이지(우리가 가진 호텔 정보(이미지 포함)가 나오고, 이미지를 클릭하면 호텔 자세히 보기로 넘어감, 호텔 CRUD)
- 리뷰 CRUD  
- 평점 (2월 20일부터 구현)

## 김우람

- 호텔 자세히 보기(객실도 여기서 나와줘야함, 리뷰도 여기서 필요)

- 객실 자세히 보기(호텔 자세히 보기에서 객실 사진을 클릭해서 객실 자세히 보기로 넘어옴)

- 호텔이랑 리뷰를 join해서 가져와서 자세히 보기에 보여줘야함  

- 예약 (2월 20일부터 구현) - axios , RestAPI로 구성

## 김재우

- 회원 가입 페이지
- 로그인 페이지

- 배치 프로그램 - 뼈대 잡음
- OpenAPI 데이터 뽑아오기 - 끝난듯
- 업주 페이지 {호텔정보(객실,이미지) 저장페이지} -- 2월 20일
- 레디스 세션공유 -- 2월 20일





