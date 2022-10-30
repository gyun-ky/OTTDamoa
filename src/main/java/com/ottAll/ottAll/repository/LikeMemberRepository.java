package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.entity.LikeMember;
import com.ottAll.ottAll.entity.Media;
import com.ottAll.ottAll.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeMemberRepository extends JpaRepository<LikeMember, Long> {

    boolean existsByMemberAndMediaAndStatus(Member member, Media media, LikeMember.Status status);
    Optional<LikeMember> findByMemberAndMediaAndStatus(Member member, Media media, LikeMember.Status status);
}
