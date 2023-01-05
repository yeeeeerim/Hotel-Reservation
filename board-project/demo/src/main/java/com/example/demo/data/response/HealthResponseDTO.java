package com.example.demo.data.response;

import com.example.demo.domain.HealthInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class HealthResponseDTO {

    private Long id;
    private String brand_name;
    private String land_number;
    private String road_number;
    private String category;

    public static HealthInfo toEntity(HealthResponseDTO healthResponseDTO){
        return HealthInfo.builder()
                .id(healthResponseDTO.getId())
                .brand_name(healthResponseDTO.getBrand_name())
                .land_number(healthResponseDTO.getLand_number())
                .road_number(healthResponseDTO.getRoad_number())
                .category(healthResponseDTO.getCategory())
                .build();

    }

}
