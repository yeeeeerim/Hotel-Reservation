package org.hotel.back.data.request;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterData {

    @Email(message = "이메일형식이 아님")
    private String email;
    private String password;

    @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
    private String tellNumber;

    @NotBlank(message = "성별을 선택해주세요")
    private String gender;
    private String nickName;

}
