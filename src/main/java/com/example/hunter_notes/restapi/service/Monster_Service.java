package com.example.hunter_notes.restapi.service;

import com.example.hunter_notes.restapi.model.Monster;
import com.example.hunter_notes.restapi.repository.Monster_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Monster_Service {
    @Autowired
    private Monster_Repository monster_repository;

    public List<Monster> getAllMonsters() {
        return monster_repository.findAll();
    }

    public Monster getMonsterByName(String name) {
        return monster_repository.findByName(name).orElse(null);
    }

    public Monster createMonster(Monster monster) {
        return monster_repository.save(monster);
    }

    public void deleteMonster(Long id) {
        monster_repository.deleteById(id);
    }
}
