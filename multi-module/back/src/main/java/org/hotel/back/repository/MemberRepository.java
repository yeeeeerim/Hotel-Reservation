package org.hotel.back.repository;

import org.hotel.back.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {


    @EntityGraph(attributePaths = {"roleSet"})
    @Query("SELECT distinct m FROM Member m WHERE m.email = :email")
    public Optional<Member> getMember(@Param("email") String email);
    public boolean existsByEmail(String email);
}
