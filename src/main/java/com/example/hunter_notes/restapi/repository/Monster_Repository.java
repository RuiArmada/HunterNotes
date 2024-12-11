package com.example.hunter_notes.restapi.repository;

import com.example.hunter_notes.restapi.model.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Monster_Repository extends JpaRepository<Monster, Long> {
    Optional<Monster> findByName(String name);
}
