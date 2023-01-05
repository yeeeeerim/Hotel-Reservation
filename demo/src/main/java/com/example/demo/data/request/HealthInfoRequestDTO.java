package com.example.demo.data.request;

import com.example.demo.domain.HealthInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

//@Builder
//@Setter
//@AllArgsConstructor
//@ToString //어노테이션을 없애니 save와 update가 정상작동했다.. 어떻게 된걸까
public class HealthInfoRequestDTO {
    //HealthInfo 데이터에 맞춘 변수선언
    private Long id;
    private String brand_name;
    private String land_number;
    private String road_number;
    private String category;

    /*toEntity 메서드 생성
    HealthInfoRequestDTO타입의 dto 매개변수를 받는다. 해당 dto는
    아래에 생성한 get메서드를 이용해서 pivate값을 호출.
    어노테이션 된 builder().build() 메서드를 이용해서
    각 값들을 엔티티값으로 만들어주는 기능.
    */
    public static HealthInfo toEntity(HealthInfoRequestDTO dto){
        return HealthInfo.builder()
                .id(dto.getId())
                .brand_name(dto.getBrand_name())
                .land_number(dto.getLand_number())
                .road_number(dto.getRoad_number())
                .category(dto.getCategory())
                .build();
    }

    public Long getId(){
        return this.id;
    }

    public String getBrand_name(){
        return this.brand_name;
    }

    public String getLand_number(){
        return this.land_number;
    }

    public String getRoad_number(){
        return this.road_number;
    }

    public String getCategory(){
        return this.category;
    }

    /* getter와 같은 역할을 하는걸까? id 값은 왜 따로 지정했을까? id로 입력하면 안됨
       id값은 private라 매개변수로 받아들일 수 없는데 왜 다른 private 변수는 될까?
       id값은 Long 이라 초기값을 지정해주는걸까?
       추적해보니 test 파일에서 테스트를 위한 값입력에 사용되었다.
     */
//    public static HealthInfoRequestDTO of(String brand_name,String land_number,
//                                          String road_number, String category){
//        return new HealthInfoRequestDTO(1L, brand_name, land_number, road_number, category);
//    }
}
