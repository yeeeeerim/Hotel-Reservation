package org.hotel.back.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KaKaoAddressData {

    private Document document;


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Document{
        @JsonProperty("region_type") private String type;
        @JsonProperty("address_name") private String addressName;
        @JsonProperty("region_1depth_name") private String firstRegion;
        @JsonProperty("region_2depth_name") private String twoRegion;
        @JsonProperty("region_3depth_name") private String threeRegion;
        @JsonProperty("region_4depth_name") private String addRegion;
        @JsonProperty("code") private String code;
        @JsonProperty("x") private String x;
        @JsonProperty("y") private String y;

    }
}
