package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    Optional<Media> findByIdAndStatus(Long id, Media.Status status);

}
