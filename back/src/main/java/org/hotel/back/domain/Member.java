package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    String member_email;
    String member_password;
    String member_tell_number;
    String member_gender;
    String member_nickname;
}
