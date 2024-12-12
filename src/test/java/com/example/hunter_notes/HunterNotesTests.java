package com.example.hunter_notes;

import com.example.hunter_notes.restapi.controller.Monster_Controller;
import com.example.hunter_notes.restapi.model.Monster;
import com.example.hunter_notes.restapi.service.Monster_Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class HunterNotesTests {

	private MockMvc mockMvc;

	@Mock
	private Monster_Service monsterService;

	@InjectMocks
	private Monster_Controller monsterController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(monsterController).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateMonster() throws Exception {
		Monster monster = new Monster("Rathalos", "Flying Wyvern", "A fire-breathing monster with red scales.");
		when(monsterService.createMonster(any(Monster.class))).thenReturn(monster);

		mockMvc.perform(post("/api/monsters")
						.contentType("application/json")
						.content("{\"name\":\"Rathalos\",\"type\":\"Flying Wyvern\",\"description\":\"A fire-breathing monster with red scales.\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("Rathalos"))
				.andExpect(jsonPath("$.type").value("Flying Wyvern"))
				.andExpect(jsonPath("$.description").value("A fire-breathing monster with red scales."));

		verify(monsterService, times(1)).createMonster(any(Monster.class));
	}

	@Test
	void testGetAllMonsters() throws Exception {
		Monster monster1 = new Monster("Rathalos", "Flying Wyvern", "A fire-breathing monster with red scales.");
		Monster monster2 = new Monster("Zinogre", "Fanged Wyvern", "A thunder-infused monster with great speed.");
		List<Monster> monsters = Arrays.asList(monster1, monster2);
		when(monsterService.getAllMonsters()).thenReturn(monsters);

		mockMvc.perform(get("/api/monsters"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Rathalos"))
				.andExpect(jsonPath("$[1].name").value("Zinogre"));

		verify(monsterService, times(1)).getAllMonsters();
	}

	@Test
	void testGetMonsterByName() throws Exception {
		Monster monster = new Monster("Rathalos", "Flying Wyvern", "A fire-breathing monster with red scales.");
		when(monsterService.getMonsterByName("Rathalos")).thenReturn(monster);

		mockMvc.perform(get("/api/monsters/Rathalos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Rathalos"))
				.andExpect(jsonPath("$.type").value("Flying Wyvern"))
				.andExpect(jsonPath("$.description").value("A fire-breathing monster with red scales."));

		verify(monsterService, times(1)).getMonsterByName("Rathalos");
	}

	@Test
	void testGetMonsterByName_NotFound() throws Exception {
		when(monsterService.getMonsterByName("NonExistentMonster")).thenReturn(null);

		mockMvc.perform(get("/api/monsters/NonExistentMonster"))
				.andExpect(status().isNotFound());

		verify(monsterService, times(1)).getMonsterByName("NonExistentMonster");
	}

	@Test
	void testDeleteMonster() throws Exception {
		Long monsterId = 100L;

		// Simulate that the monster exists and can be deleted
		doNothing().when(monsterService).deleteMonster(monsterId);
		when(monsterService.getMonsterById(monsterId)).thenReturn(new Monster());  // Mock the monster exists

		// Perform DELETE request
		ResultActions resultActions = mockMvc.perform(delete("/api/monsters/" + monsterId))
				.andExpect(status().isNoContent());  // Expect 204 No Content status

		// Verify the service method was called once
		verify(monsterService, times(1)).deleteMonster(monsterId);
	}

	@Test
	void testDeleteMonster_NotFound() throws Exception {
		Long monsterId = 100L;

		// Simulate that the monster doesn't exist
		when(monsterService.getMonsterById(monsterId)).thenReturn(null);  // Mock the monster doesn't exist

		// Perform DELETE request
		ResultActions resultActions = mockMvc.perform(delete("/api/monsters/" + monsterId))
				.andExpect(status().isNotFound());  // Expect 404 Not Found status

		// Verify that delete was not called
		verify(monsterService, times(0)).deleteMonster(monsterId);
	}

}
