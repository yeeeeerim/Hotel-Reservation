package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    @Id
    String memberEmail;
    String memberPassword;
    String memberTellNumber;
    String memberGender;
    String memberNickname;
}
