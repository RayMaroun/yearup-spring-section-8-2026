package com.pluralsight.gamelibrary.controllers;

import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.service.GameService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@CrossOrigin
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // GET /api/games -> every game, as a JSON array.
    @GetMapping
    public List<Game> getAll() {
        return gameService.allGames();
    }
}
