package org.hotel.back.service.security;

import lombok.RequiredArgsConstructor;
import org.hotel.back.data.dto.MemberDTO;
import org.hotel.back.domain.Member;
import org.hotel.back.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.getMember(username).orElseThrow(() -> new UsernameNotFoundException("없는 유저"));

        return MemberDTO.toDTO(member);
    }
}
