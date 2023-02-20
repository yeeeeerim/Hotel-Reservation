package org.hotel.back.data.request;


import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelRequestData{
    String key;
    String type;
    String pIndex;
    String pSize;

    public MultiValueMap<String,String> toMultiValueMap() {
            var map = new LinkedMultiValueMap<String, String>();
            map.add("KEY", key);
            map.add("Type", type);
            map.add("pIndex", pIndex);
            map.add("pSize", pSize);

            return map;
        }
}
