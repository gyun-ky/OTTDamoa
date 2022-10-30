package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.entity.MemberPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPlatformRepository extends JpaRepository<MemberPlatform, Long> {
}
