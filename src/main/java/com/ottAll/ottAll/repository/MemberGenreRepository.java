package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.entity.MemberGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberGenreRepository extends JpaRepository<MemberGenre, Long> {
}
