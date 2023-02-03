package org.hotel.back.data.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FileDTO {
    private String uuid;
    private String fileName;
}
