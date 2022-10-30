package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.entity.MemberActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberActorRepository extends JpaRepository<MemberActor, Long> {
}
