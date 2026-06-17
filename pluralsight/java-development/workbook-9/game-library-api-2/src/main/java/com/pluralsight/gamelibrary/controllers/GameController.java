package com.pluralsight.gamelibrary.controllers;

import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.service.GameService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // GET /api/games                       -> all games
    // GET /api/games?year=2020&genre=RPG   -> filters combine
    // GET /api/games?genre=RPG&sort=rating -> filter + sort
    @GetMapping
    public List<Game> getAll(@RequestParam(required = false) Integer year,
                             @RequestParam(required = false) String genre,
                             @RequestParam(required = false) Double minRating,
                             @RequestParam(required = false) String sort) {
        return gameService.search(year, genre, minRating, sort);
    }

    // GET /api/games/{id} -> one game (a missing id returns null for now -> an
    // empty 200; Module 4 turns that into a clean 404).
    @GetMapping("/{id}")
    public Game getById(@PathVariable long id) {
        return gameService.byId(id);
    }
}
