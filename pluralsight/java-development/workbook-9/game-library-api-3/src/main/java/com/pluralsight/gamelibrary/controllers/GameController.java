package com.pluralsight.gamelibrary.controllers;

import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping
    public List<Game> getAll(@RequestParam(required = false) Integer year,
                             @RequestParam(required = false) String genre,
                             @RequestParam(required = false) Double minRating,
                             @RequestParam(required = false) String sort) {
        return gameService.search(year, genre, minRating, sort);
    }

    @GetMapping("/{id}")
    public Game getById(@PathVariable long id) {
        return gameService.byId(id);
    }

    // 201 Created, with the saved game (now carrying its id) in the body.
    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        Game saved = gameService.createGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 200 OK with the updated game.
    @PutMapping("/{id}")
    public Game update(@PathVariable long id, @RequestBody Game game) {
        return gameService.updateGame(id, game);
    }

    // 204 No Content - success, nothing to return.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
