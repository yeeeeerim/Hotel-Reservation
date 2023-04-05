package com.back.bookingmodule.service.security;


import com.back.bookingmodule.data.MemberDTO;
import com.back.bookingmodule.domain.Member;
import com.back.bookingmodule.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class SecuriityService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("++>> {}",username);
        Member member = memberRepository.getMember(username).orElseThrow(() -> new UsernameNotFoundException("없는 유저"));
        log.info("=>>> Member {}",member);
        return MemberDTO.toDTO(member);
    }
}
