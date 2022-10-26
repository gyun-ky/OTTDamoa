package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserIdAndStatus(String userId, Member.Status status);
    Optional<Member> findByUserIdAndRefreshTokenAndStatus(String userId, String refreshToken, Member.Status status);
}
