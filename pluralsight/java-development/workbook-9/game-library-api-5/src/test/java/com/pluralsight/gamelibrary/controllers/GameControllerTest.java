package com.pluralsight.gamelibrary.controllers;

import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.models.Genre;
import com.pluralsight.gamelibrary.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GameService gameService;

    @Test
    void getAll_GamesExist_ReturnsThemAsJson() throws Exception {
        // Arrange
        Game elden = new Game("Elden Ring", 2022, 9.6, new Genre("RPG"));
        Game celeste = new Game("Celeste", 2018, 9.1, new Genre("Indie"));
        when(gameService.search(null, null, null, null)).thenReturn(List.of(elden, celeste));

        // Act + Assert (MockMvc fuses sending the request and checking the response)
        mockMvc.perform(get("/api/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Elden Ring"))
                .andExpect(jsonPath("$[1].title").value("Celeste"));
    }

    @Test
    void getById_MissingId_Returns404() throws Exception {
        // Arrange
        when(gameService.byId(99L)).thenReturn(null);

        // Act + Assert
        mockMvc.perform(get("/api/games/99"))
                .andExpect(status().isNotFound());
    }
}
