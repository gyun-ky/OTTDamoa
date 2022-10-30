package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {

    List<Platform> findAllByStatus(Platform.Status status);

}
