package org.hotel.back.config;

import lombok.extern.slf4j.Slf4j;
import org.hotel.back.data.dto.MemberDTO;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Component
public class MemberAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //TODO:2/23 수정필요
        if (authentication != null || authentication.isAuthenticated()) {
            log.info("JPA AUDTOR ==>> {}", authentication.getPrincipal());
            if (!authentication.getPrincipal().equals("anonymousUser")) {
                MemberDTO dto = (MemberDTO) authentication.getPrincipal();
                return Optional.of(dto.getEmail());
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();}

}
