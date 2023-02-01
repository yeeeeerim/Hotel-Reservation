package org.hotel.back.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member {
    @Id
    private String memberEmail;
    private String memberPassword;
    private String memberTellNumber;
    private String memberGender;
    private String memberNickname;

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roleSet = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "member")
    private List<Booking> bookingList = new ArrayList<>();

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

}
