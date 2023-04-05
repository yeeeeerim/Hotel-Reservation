package com.back.bookingmodule.repository;

import com.back.bookingmodule.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String > {

    @EntityGraph(attributePaths = {"roleSet"})
    @Query("SELECT distinct m FROM Member m WHERE m.email = :email")
    public Optional<Member> getMember(@Param("email") String email);
}
