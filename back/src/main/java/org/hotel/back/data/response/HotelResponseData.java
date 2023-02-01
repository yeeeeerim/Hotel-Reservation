package org.hotel.back.data.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.DataAmount;
import lombok.*;
import org.hotel.back.domain.Hotel;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseData {
        public List<GSST> GSST;
        @Data
       public static class GSST{
               @JsonProperty("head") List<Head> head;
               @JsonProperty("row") List<Row> rowList;
       }
        @Data
        public static class Head{
                @JsonProperty("list_total_count") String LIST_TOTAL_COUNT;
                @JsonProperty("RESULT") Result RESULT;
                @JsonProperty("api_version") String API_VERSION;;
        }
        @Data
        public static class Result{
            @JsonProperty("CODE")String CODE;
            @JsonProperty("MESSAGE")String MESSAGE;

        }
        @Data
        public static class Row {
            @JsonProperty("SIGUN_NM")String SIGUN_NM;
            @JsonProperty("ENTRPS_NM")String ENTRPS_NM;
            @JsonProperty("TELNO")String TELNO;
            @JsonProperty("DATA_STD_DE")String DATA_STD_DE;
            @JsonProperty("REFINE_WGS84_LAT")String REFINE_WGS84_LAT;
            @JsonProperty("REFINE_WGS84_LOGT")String REFINE_WGS84_LOGT;
        }



}
