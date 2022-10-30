package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findAllByStatus(Genre.Status status);

}
