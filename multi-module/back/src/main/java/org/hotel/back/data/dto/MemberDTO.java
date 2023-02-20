package org.hotel.back.data.dto;


import lombok.*;
import org.hotel.back.domain.Gender;
import org.hotel.back.domain.Member;
import org.hotel.back.domain.MemberRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class MemberDTO extends User {


    private String email;

    private String password;

    private String tellNumber;

    private Gender gender;

    private String nickName;


    public MemberDTO(String email, String password,
                     String tellNumber,
                     Collection<? extends GrantedAuthority> authorities,
                     Gender gender,
                     String nickName){
        super(email,password,authorities);
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.nickName = nickName;
    }

    public static MemberDTO toDTO(Member member){
            return new MemberDTO(
                    member.getEmail(),
                    member.getPassword(),
                    member.getTellNumber(),
                    member.getRoleSet().stream().map(memberRole
                                    -> new SimpleGrantedAuthority("ROLE_"+memberRole.getRole())).
                            collect(Collectors.toList()),
                    member.getGender(),
                    member.getNickName()
            );
    }



}
