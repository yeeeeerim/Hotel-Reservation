## Mybatis와 JPA의 차이
우선 영속성(Persistence)에 대해서 알아야합니다.
데이터를 생성한 프로그램이 종료되더라도 사라지지 않는 데이터 특성을 Persistence라고 합니다.
Java에서 Persistence Layer를 구현하는 방법은 크게 2가지로 구분 할 수 있는데
1. JDBC(Java Database Connectivity) 사용
    JDBC란
    - 자바에서 데이터베이스에 접속할 수 있도록 하는 자바 API
    - 자바 프로그램이 데이터베이스와 연결되어 데이터를 주고 받을 수 있게 해주는 프로그래밍 인터페이스
    ** JDBC가 생긴 이유 : 자바로 DB에 접근하는 방법이 DB마다 커넥션 연결방법, SQL 전달방법, 결과 응답방법이 다 다름.
    만약, 데이터접근 방법이 같다면 다른 DB를 사용하더라도 코드를 수정할 일이 없기 때문에 생겨남
    - 응용프로그램과 DBMS간의 통신을 중간에서 번역해주는 역할
    - 버퍼를 통해 sql을 보내고 결과값을 가져옴
    - 데이터 저장 기술을 데이터 소스라는 추상화된 인터페이스를 통해 접근
    - 단점 : 반복적인 작업들을 계속해서 작성해야함
             JDBC가 사용하는 SQL은 String형태(오타 오류)
             SQL, DB연결, Java언어가 모두 존재해서 재사용성 안좋음
2. Persistence Framework 사용
    Persistence Framework로는 SQL Mapper와 ORM 2가지가 있음
    SQL Mapper로는 아래에 설명할 Mybatis가 있고,
    ORM은 Hibernate, EclipseLink, DataNucleus 등이 존재함
     - 패러다임 불일치에서 오는 불편한 작업을 대신해주는 데이터 접근기술
     - 관계형 DB에 담긴 정보를 객체를 다루는 것처럼 관리 할 수 있음(객체와 테이블이 매핑을 이룸)
     - 반복적인 SQL을 직접 작성할 필요 없음 (ORM이 필요한 SQL을 자동으로 생성해서 수행한 뒤 결과를 객체로 돌려줌)
     - query를 작성하지 않고 메서드 호출로만 query가 수행되어 생산성이 높아짐 다만, query가 복잡해지면 ORM으로 한계가 있어 성능이 raw query에 비해 느림


## MyBastis
1. 쿼리 기반 웹 애플리케이션을 개발할 때 가장 많이 사용되는 SQL 매퍼(Mapper) 프레임워크이다. 
- JDBC로 처리하는 상당부분의 코드와 파라미터 설정 및 결과 매핑을 대신해주기 때문.
- SQL쿼리문(in XML파일)과 자바코드(in 인터페이스)가 분리되어 재사용성이 높음
- Mybatis의 내부에서 동적으로 데이터 바인딩하여 직접 바인딩하는 작업을 하지 않아도됨
- 장점
    복잡한 SQL문도 처리할 수 있음
    관계를 정확히 명시할 수 있음
- 단점
    SQL을 개발자가 직접 장성해야함
    DBMS에 종속적
    비슷한 쿼리를 반복적으로 작성해야함

## JPA(Java Persistence API)
- 자바의 표준 ORM 기술 명세
- JPA를 사용하기 위해서는 JPA를 구현한 ORM 프레임워크를 사용해야함.