package com.example.hunter_notes.restapi.controller;

import com.example.hunter_notes.restapi.model.Monster;
import com.example.hunter_notes.restapi.service.Monster_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monsters")
public class Monster_Controller {

    @Autowired
    private Monster_Service monsterService;

    // Create Monster: Returns 201 Created status
    @PostMapping
    public ResponseEntity<Monster> createMonster(@RequestBody Monster monster) {
        Monster createdMonster = monsterService.createMonster(monster);
        return new ResponseEntity<>(createdMonster, HttpStatus.CREATED);  // 201 Created
    }

    // Get all Monsters: Returns 200 OK status
    @GetMapping
    public List<Monster> getAllMonsters() {
        return monsterService.getAllMonsters();
    }

    // Get Monster by Name: Returns 404 Not Found if not found, 200 OK if found
    @GetMapping("/{name}")
    public ResponseEntity<Monster> getMonsterByName(@PathVariable String name) {
        Monster monster = monsterService.getMonsterByName(name);
        if (monster == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found
        }
        return new ResponseEntity<>(monster, HttpStatus.OK);  // 200 OK if found
    }

    // Delete Monster: Returns 204 No Content if successfully deleted, 404 Not Found if not found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonster(@PathVariable Long id) {
        Monster monster = monsterService.getMonsterById(id);
        if (monster == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if not found
        }
        monsterService.deleteMonster(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Return 204 if deleted successfully
    }

}
