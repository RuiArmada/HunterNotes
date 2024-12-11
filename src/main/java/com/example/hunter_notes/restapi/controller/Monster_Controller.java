package com.example.hunter_notes.restapi.controller;

import java.util.List;

import com.example.hunter_notes.restapi.model.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.hunter_notes.restapi.service.Monster_Service;

@RestController
@RequestMapping("/api/monsters")
public class Monster_Controller {

    @Autowired
    private Monster_Service monsterService;

    // POST request to create a new user
    @PostMapping
    public Monster createMonster(@RequestBody Monster monster) {
        return monsterService.createMonster(monster);
    }

    @GetMapping
    public List<Monster> getAllMonsters() {
        return monsterService.getAllMonsters();
    }

    // GET request to retrieve a user by ID
    @GetMapping("/{name}")
    public Monster getMonsterByName(@PathVariable String name) {
        return monsterService.getMonsterByName(name);
    }

    // DELETE request to delete a user by ID
    @DeleteMapping("/{name}")
    public void deleteMonster(@PathVariable Long id) {
        monsterService.deleteMonster(id);
    }
}
