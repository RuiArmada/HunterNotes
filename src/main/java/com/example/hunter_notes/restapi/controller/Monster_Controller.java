package com.example.hunter_notes.restapi.controller;

import com.example.hunter_notes.restapi.model.Monster;
import com.example.hunter_notes.restapi.service.Monster_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monsters")
public class Monster_Controller {

    @Autowired
    private Monster_Service monsterService;

    @PostMapping
    public Monster createMonster(@RequestBody Monster monster) {
        return monsterService.createMonster(monster);
    }

    @GetMapping
    public List<Monster> getAllMonsters() {
        return monsterService.getAllMonsters();
    }

    @GetMapping("/{name}")
    public Monster getMonsterByName(@PathVariable String name) {
        return monsterService.getMonsterByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteMonster(@PathVariable Long id) {
        monsterService.deleteMonster(id);
    }
}
