//package org.hotel.back.service.api;
//
//import org.json.simple.parser.ParseException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//class KaKaoAPIServiceTest {
//
//    @Autowired
//    KaKaoAPIService kaKaoAPIService;
//
//    @Autowired
//    WebClient webClient;
//
//    @Test
//    @DisplayName("KAKAOAPI 위도경도 요청")
//    void test() throws ParseException {
//        System.out.println(kaKaoAPIService.getLocationInfo("미사강변대로 165"));;
//    }
//    @Test
//    @DisplayName("KAKAOAPI 위도경도를 도로명주소로")
//    void test2() throws ParseException {
//        System.out.println(kaKaoAPIService.getAddressInfo("127.10459896729914","37.40269721785548"));;
//    }
//
//    @Test
//    void testa(){
//        WebClient webClient = WebClient.builder()
//                .baseUrl("http://localhost:8090")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .build();
//        Mono<String> mono = webClient
//                .get()  //get요청
//                .uri("/test2") //해당 uri
//                .retrieve() //가져와라
//                .bodyToMono(String.class);   //모노타입<>으로 받음;
//
//
//        Mono<String> mono2 = webClient
//                .get()  //get요청
//                .uri("/test1") //해당 uri
//                .retrieve() //가져와라
//                .bodyToMono(String.class);   //모노타입<>으로 받음; 모노는 안에 값을 하나만 받음
//
//        //여기까지하면 아무일 안 일어남
//        // 리액터 api 공부 필요
//        // 스트림의 특징을 알아야함 모노는 퍼블리셔 구현체 퍼블리셔는 서브스크립스(구독)을 하기 전에는 플로우가 발생하지 않는다.
//        // 구독을 해야 실제 데이터가 흐르기 시작한다.
//        mono.doOnSuccess(s -> {
//            System.out.println(s);
//        }).subscribe();  //성공하면(응답데이터) subscribe를 해줘야 플로우가 진행됨
//
//    }
//
//    @Test
//    void webCLientBlock(){
//          String str = webClient
//                    .get()
//                    .uri("/test2").retrieve().bodyToMono(String.class).block();
//
//        System.out.println(str);
//    }
//}