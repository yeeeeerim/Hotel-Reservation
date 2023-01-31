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
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int memberRoleId;
    int memberId;
    String roleName;
}
