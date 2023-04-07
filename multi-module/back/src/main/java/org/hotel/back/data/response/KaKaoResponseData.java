package org.hotel.back.data.response;


import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KaKaoResponseData {


    private String longitude;

    private String latitude;

}
