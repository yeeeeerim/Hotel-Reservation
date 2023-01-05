package com.example.demo.data.response;

import com.example.demo.data.request.HealthInfoRequestDTO;
import com.example.demo.domain.HealthInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class HealthInfoResponseDTO { //

    private Long id;
    private String brand_name;
    private String land_number;
    private String road_number;
    private String category;

    //앞선 RequestDTO에서 각 get 메서드가 선언되어있기때문에
    public static HealthInfo toEntity(HealthInfoResponseDTO healthInfoResponseDTO){
        return HealthInfo.builder()
                    .id(healthInfoResponseDTO.getId())
                    .brand_name(healthInfoResponseDTO.getBrand_name())
                    .land_number(healthInfoResponseDTO.getLand_number())
                    .road_number(healthInfoResponseDTO.getRoad_number())
                    .category(healthInfoResponseDTO.getCategory())
                    .build();
    }
}
