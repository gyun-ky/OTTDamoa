package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Actor> findAllByStatus(Actor.Status status);
}
