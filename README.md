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
![image](https://user-images.githubusercontent.com/79129475/226362885-dfc4dd89-168c-4fab-842b-a38a3dbb163b.png)

8080 서버에서 MVC로 화면 그리고 각 기능들을 가지고 있음 --> Spring MVC
8090 서버는 배치 프로그램과 호텔 예약 저장 기능과 삭제 기능만 만들예정 --> RESTAPI



## ERD 
![image](https://user-images.githubusercontent.com/79129475/228429497-fc381b6a-bdce-49cf-99b3-31b24186a0b0.png)


## 작성법
브랜치 이름 ->  FEAT: 브랜치이름(소문자)  브랜치 이름은 기능명
 혼자 Merge 절대 금지 *****
 메소드 설명 적어주기(주석으로)
 절대 복붙 금지(중요)
 
## 할 일 (하다가 생기면 적을 예정)
 
- docker-compose mysql, redis -> AWS 배포테스트
- Hotel(Image), Review -> 4/4 코드  정리예정
- Rating 진행 후 -> 코드 정리예정

> 정리 규칙
모든 메소드에 주석으로 설명 달기 @param, @apiNote @author
필요없는 안 쓰는 코드 지우기 
ERD 최신화 필요
 
4/4 ~ 4/7
Controller, Service, Repository 테스트

그 다음 주

AWS 배포(EC2, RDS , S3)


다다음주
배포환경에서 결겪은 에러해결




## 김우람

- 호텔 자세히 보기(객실도 여기서 나와줘야함, 리뷰도 여기서 필요)

- 객실 자세히 보기(호텔 자세히 보기에서 객실 사진을 클릭해서 객실 자세히 보기로 넘어옴)

- 호텔이랑 리뷰를 join해서 가져와서 자세히 보기에 보여줘야함  

-  예약 CRUD 만들기, RestAPI로 구성
  (클라이언트가 원하는 날짜를 정해서 서브서버(8090)에 요청을 날림, 서버는 원하는 날짜에 예약이 없는 객실정보를 다 날리기, 그러고 클라이언트가 객실 리스트 중에서 선택을 한다. ) -객실리스트 페이지에서 JS 수정예정  

