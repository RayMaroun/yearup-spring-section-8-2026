package com.pluralsight.gamelibrary.controllers;

import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@CrossOrigin
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // PUBLIC - anyone may read.
    @GetMapping
    @PreAuthorize("permitAll()")
    public List<Game> getAll(@RequestParam(required = false) Integer year,
                             @RequestParam(required = false) String genre,
                             @RequestParam(required = false) Double minRating,
                             @RequestParam(required = false) String sort) {
        return gameService.search(year, genre, minRating, sort);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public Game getById(@PathVariable long id) {
        Game game = gameService.byId(id);
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No game with id " + id);
        }
        return game;
    }

    // AUTHENTICATED - any logged-in user may create or update.
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Game> create(@Valid @RequestBody Game game) {
        Game saved = gameService.createGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Game update(@PathVariable long id, @Valid @RequestBody Game game) {
        Game saved = gameService.updateGame(id, game);
        if (saved == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No game with id " + id);
        }
        return saved;
    }

    // ADMIN ONLY - only an administrator may delete.
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (gameService.byId(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No game with id " + id);
        }
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
