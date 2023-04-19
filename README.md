#패스트캠퍼스교육 스터디활동


# 공공데이터 사이트
https://data.gg.go.kr/portal/data/service/selectServicePage.do?page=1&rows=10&sortColumn=&sortDirection=&infId=LU6OWRJ132QKLU6TASZX31096522&infSeq=3&order=&loc=&searchWord=%EC%88%99%EB%B0%95

# 프로젝트 기술
<hr>

<p>
	
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)<img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"><img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white"><img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
<br>
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"><img src="https://img.shields.io/badge/gradle-02303A.svg?&style=for-the-badge&logo=gradle&logoColor=white"><img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"><img src="https://img.shields.io/badge/spring data jpa-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<br>![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)<img src="https://img.shields.io/badge/aws-232F3E.svg?&style=for-the-badge&logo=amazonaws&logoColor=white">

</p>

## 동작 방식

![제목을-입력해주세요_-001 (1)](https://user-images.githubusercontent.com/95463650/232680670-4218bcff-94b9-4395-bf7a-1bb6d21ea6de.jpg)

- Redis
   
객실 데이터를 조회할 떄 해당 데이터를 바로 Mysql에서 가져오는 것이 아닌 Redis를 먼저 조회하고 없을 경우 Mysql에서 가져오는 방식으로 구성했다. 처음 조회할 떄는 Redis를 조회하고 Mysql을 조회하지만 그 다음 사용자가 조회할 때는 계속 Redis만 조회하도록 구성했다. 

- Spring Batch 

Spring 스케줄링을 통해 하루에 한 번씩 기간이 지난 예약 데이터들을 삭제하는 방식으로 구현했고, 처음에 호텔 Open API 데이터를 받는 것을
8080 서버에서 실행할 때 다 받아오도록 하려했는데 중간에 OpenApi 중단이 끊기게 되어서 해당 데이터를 사용하려 했는데 사용 못하게 되었었다.
그래서 현재 PC에 있는 호텔 데이터를 CSV 파일로 Batch 프로그램을 돌려 생성하고,  만든 CSV 파일을 통해 Mysql 컨테이너에 데이터를 넣었다.

- KaKao API 활용

 Open API에 데이터는 위도 경도는 주지만 자세한 위치가 안 나오는 곳이 있어 해당 값으로 KaKao map으로 보여주기도 하고 해당 위도, 경도 데이터로 요청해서 도로명 주소를 받아오도록 구성했다.  


## ERD 
![image](https://user-images.githubusercontent.com/79129475/228429497-fc381b6a-bdce-49cf-99b3-31b24186a0b0.png)

## 배포기록
<hr>
https://jaewoo2233.tistory.com/105



## 느낀점 


김재우
<hr>

>  서비스를 제공하는 프로젝트를 만들어보기 보다는 Spring Batch, Redis , Spring Scheduler를 어떻게 사용하면 좋을지 생각해보면서 
프로젝트를 진행했다. 여러번 시도를 통해 어떤 방식으로 구현하면 좋을지 많이 생각해보는 계기가 되었고, 다음 프로젝트에 더 좋은 방식으로
해당 기술들을 적용시킬 준비를 했다. 그러면서 AWS EC2, S3 버킷등도 처음으로 배포하며 진행해봤다. 배포를 진행하면서도 많이 배웠는데 그 중 
가장 큰게 메모리인 거 같다. 기본 노트북은 못해도 메모리가 8GB인데 배포환경은 1GB인 경우도 있어서 전혀 생각 못하고 있었다. 배포 중 직접
메모리 부족을 겪고 보니 메모리 분할이 중요하다는 걸 알았다. 현 프로젝트를 통해 많이 배우고 생각해볼 수 있어서 좋았다.
<hr>

김우람
<hr>

> 프로그래밍을 공부하면서 지금까지 뭐배웠어라고 누군가 물어본다면 대답할 만한 내용이 쉽게 떠오르지 않았는데 이번 스터디 조원들과 같이 프로젝트를 진행하면서 springboot를 사용한 웹개발에 기초에 대해서는 많이 머리속에 들어 왔다. 그 덕분에 각 레이어별로 나눠야하는 이유, 기본적인 CRUD구성은 이제 혼자 힘으로 할 수 있고, 양방향 매핑의 올바른 사용방법, MVC와 restController로 데이터를 통신하는 방법을 알게 됐고, 직접 화면도 건드리다보니 javascript도 기본적인 사용법을 익히게 되었다.

> 프로젝트를 진행하면서 가장 힘들었던 점은 내가 무엇을 모르는 지를 정확하게 알지 못해서 어디서부터 공부를 해야할지 몰랐던 것이 나를 가장 힘들게 했고, 그만두고 싶다는 생각을 계속 하게 되는 원인이었지만, 같은 스터디조원들이 기능구현하는 동안 기다려주고 문제가 생겼을 때 같이 해결해 주는 등 도움을 주는 덕분에 끝까지 프로젝트에 참여를 할 수 있게 되었다. 앞으로 다른 프로젝트를 진행 할 때는, 이번 프로젝트에서 배운 점을 많이 활용하고, 더욱 더 공부해서 혹시 헤매는 조원이 있을 경우 같이 도와주면서 해결 해나가는 능력을 기를 수 있게 된 좋은 경험이었다.

<hr>
고예림
<hr>

> 처음접하는 스프링부트 프로젝트다보니 처음에 Controller, Service, Repository등의 이해가 어려웠었다. 강의를 들으면서 따라하는 것과 직접 코딩을 하며 기능 구현을 하는것이 천지차이라는 것을 알게되었고 직접해보며 여러 시행착오를 겪으며 스프링부트에 대한 전반적인 이해를 할 수 있었고, MVC로 화면에 띄우는 것도 해보고 restAPI로 데이터를 보내주는 것도 해보면서 많은 경험을 할 수 있었다. 부족한 것도 많았지만 쿼리수를 줄이기 위해 fetch join, batch size도 사용해보고 redis로 캐시데이터 호텔이미지 관리도 해보며 무작정 기능을 구현하면 되는 것이 아닌 다른것들도 생각해보며 개발을 해야한다는 것들도 배운 좋은 프로젝트였다 😀 





