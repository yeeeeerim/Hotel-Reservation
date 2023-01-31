package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberRole {
    int member_role_id;
    int member_id;
    String role_name;
}
