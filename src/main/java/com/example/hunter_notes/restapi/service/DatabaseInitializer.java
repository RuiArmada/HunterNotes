package com.example.hunter_notes.restapi.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @jakarta.annotation.PostConstruct
    public void init() {
        String[] monsters = {
                "('Rathalos', 'Flying Wyvern', 'A fire-breathing monster with red scales.')",
                "('Zinogre', 'Fanged Wyvern', 'A thunder-infused monster with great speed.')",
                "('Nergigante', 'Elder Dragon', 'A monstrous, spiked dragon known for its destructive power.')",
                "('Tigrex', 'Brute Wyvern', 'A fierce beast with a powerful charge attack.')",
                "('Anjanath', 'Brute Wyvern', 'A fire-breathing monster resembling a dinosaur.')",
                "('Diablos', 'Flying Wyvern', 'A massive wyvern with dangerous horns.')",
                "('Velkhana', 'Elder Dragon', 'A frost-infused dragon from the icy tundra.')",
                "('Kushala Daora', 'Elder Dragon', 'A wind-infused elder dragon.')",
                "('Rajang', 'Fanged Beast', 'A lightning-charged, angry beast.')",
                "('Bazelgeuse', 'Flying Wyvern', 'A bomb-carrying monster that drops explosives.')",
                "('Vaal Hazak', 'Elder Dragon', 'A dragon whose body is shrouded in noxious fumes.')",
                "('Mizutsune', 'Fanged Wyvern', 'A bubbly, water-infused monster.')",
                "('Barroth', 'Brute Wyvern', 'A heavy, mud-caked beast with a dangerous charge.')",
                "('Lunastra', 'Elder Dragon', 'A fiery elder dragon that partners with Teostra.')",
                "('Teostra', 'Elder Dragon', 'A fire-infused dragon that can unleash explosive flames.')",
                "('Pukei-Pukei', 'Bird Wyvern', 'A venomous wyvern with a long tongue.')",
                "('Great Jagras', 'Fanged Wyvern', 'A scavenger wyvern that swallows prey whole.')",
                "('Paolumu', 'Flying Wyvern', 'A bat-like wyvern that inflates its neck like a balloon.')",
                "('Legiana', 'Flying Wyvern', 'A sleek ice-infused wyvern with sharp claws.')",
                "('Tobi-Kadachi', 'Fanged Wyvern', 'A lightning-charged wyvern that glides through trees.')",
                "('Odogaron', 'Fanged Wyvern', 'A terrifying wyvern with razor-sharp claws.')",
                "('Behemoth', 'Elder Dragon', 'A legendary beast from another world.')",
                "('Barioth', 'Flying Wyvern', 'A saber-toothed wyvern from icy regions.')",
                "('Deviljho', 'Brute Wyvern', 'A relentless predator driven by hunger.')",
                "('Glavenus', 'Brute Wyvern', 'A fearsome wyvern with a fiery, sword-like tail.')",
                "('Brachydios', 'Brute Wyvern', 'A wyvern that uses explosive slime to attack.')",
                "('Uragaan', 'Brute Wyvern', 'A lava-dwelling wyvern with a powerful chin slam.')",
                "('Lavasioth', 'Piscine Wyvern', 'A lava fish that swims through molten rock.')",
                "('Dodogama', 'Brute Wyvern', 'A docile wyvern that spits explosive rocks.')",
                "('Kirin', 'Elder Dragon', 'A mystical, lightning-infused unicorn dragon.')",
                "('Shara Ishvalda', 'Elder Dragon', 'An ancient dragon that uses vibrations to attack.')",
                "('Namielle', 'Elder Dragon', 'A glowing, water-infused elder dragon.')",
                "('Alatreon', 'Elder Dragon', 'An elder dragon capable of controlling multiple elements.')",
                "('Fatalis', 'Elder Dragon', 'A legendary black dragon that brought kingdoms to ruin.')"
        };

        for (String monster : monsters) {
            // Extract the monster's name from the string for the query
            String monsterName = monster.split(",")[0].replace("(", "").replace("'", "");

            // Check if the monster already exists in the database
            String checkSql = "SELECT COUNT(*) FROM monsters WHERE name = ?";
            Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, monsterName);

            // If the monster exists, update the existing record, otherwise insert it
            if (count != null && count > 0) {
                String updateSql = "UPDATE monsters SET type = ?, description = ? WHERE name = ?";
                jdbcTemplate.update(updateSql,
                        monster.split(",")[1].replace("'", ""),
                        monster.split(",")[2].replace("'", "").replace(")", ""),
                        monsterName);
            } else {
                String insertSql = "INSERT INTO monsters (name, type, description) VALUES " + monster;
                jdbcTemplate.execute(insertSql);
            }
        }
    }
}
