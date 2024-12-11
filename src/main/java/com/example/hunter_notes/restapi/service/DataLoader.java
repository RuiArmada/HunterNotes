package com.example.hunter_notes.restapi.service;

import com.example.hunter_notes.restapi.model.Monster;
import com.example.hunter_notes.restapi.repository.Monster_Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(Monster_Repository monsterRepository) {
        return args -> {
            monsterRepository.save(new Monster("Rathalos", "Flying Wyvern", "A fire-breathing monster with red scales."));
            monsterRepository.save(new Monster("Zinogre", "Fanged Wyvern", "A thunder-infused monster with great speed."));
            monsterRepository.save(new Monster("Nergigante", "Elder Dragon", "A monstrous, spiked dragon known for its destructive power."));
            monsterRepository.save(new Monster("Tigrex", "Brute Wyvern", "A fierce beast with a powerful charge attack."));
            monsterRepository.save(new Monster("Anjanath", "Brute Wyvern", "A fire-breathing monster resembling a dinosaur."));
            monsterRepository.save(new Monster("Diablos", "Flying Wyvern", "A massive wyvern with dangerous horns."));
            monsterRepository.save(new Monster("Velkhana", "Elder Dragon", "A frost-infused dragon from the icy tundra."));
            monsterRepository.save(new Monster("Kushala Daora", "Elder Dragon", "A wind-infused elder dragon."));
            monsterRepository.save(new Monster("Rajang", "Fanged Beast", "A lightning-charged, angry beast."));
            monsterRepository.save(new Monster("Bazelgeuse", "Flying Wyvern", "A bomb-carrying monster that drops explosives."));
            monsterRepository.save(new Monster("Vaal Hazak", "Elder Dragon", "A dragon whose body is shrouded in noxious fumes."));
            monsterRepository.save(new Monster("Mizutsune", "Fanged Wyvern", "A bubbly, water-infused monster."));
            monsterRepository.save(new Monster("Barroth", "Brute Wyvern", "A heavy, mud-caked beast with a dangerous charge."));
            monsterRepository.save(new Monster("Lunastra", "Elder Dragon", "A fiery elder dragon that partners with Teostra."));
            monsterRepository.save(new Monster("Teostra", "Elder Dragon", "A fire-infused dragon that can unleash explosive flames."));
        };
    }
}
