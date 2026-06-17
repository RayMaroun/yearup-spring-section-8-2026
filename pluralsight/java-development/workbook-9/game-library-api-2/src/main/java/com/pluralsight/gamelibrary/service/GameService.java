package com.pluralsight.gamelibrary.service;

import com.pluralsight.gamelibrary.data.GameRepository;
import com.pluralsight.gamelibrary.data.GenreRepository;
import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.models.Genre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;

    public GameService(GameRepository gameRepository, GenreRepository genreRepository) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
    }

    // Adapted for the web: returns null when missing (the controller turns it into a 404).
    public Game byId(long id) {
        return gameRepository.findById(id).orElse(null);
    }

    // One combinable stream search, replacing last week's per-field finders.
    public List<Game> search(Integer year, String genre, Double minRating, String sort) {
        List<Game> results = new ArrayList<>(gameRepository.findAll().stream()
                .filter(g -> year == null || g.getReleaseYear() == year)
                .filter(g -> genre == null
                        || (g.getGenre() != null && g.getGenre().getName().equalsIgnoreCase(genre)))
                .filter(g -> minRating == null || g.getRating() >= minRating)
                .toList());

        if ("rating".equalsIgnoreCase(sort)) {
            results.sort(Comparator.comparingDouble(Game::getRating).reversed());
        } else if ("title".equalsIgnoreCase(sort)) {
            results.sort(Comparator.comparing(Game::getTitle));
        }
        return results;
    }

    // Still last week's write methods - rebuilt for the web in Module 4.
    public Game addGame(String title, int year, double rating, long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new NotFoundException("No genre with id " + genreId));
        return gameRepository.save(new Game(title, year, rating, genre));
    }

    public Game updateRating(long id, double rating) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No game with id " + id));
        game.setRating(rating);
        return gameRepository.save(game);
    }

    public void deleteGame(long id) {
        if (!gameRepository.existsById(id)) {
            throw new NotFoundException("No game with id " + id);
        }
        gameRepository.deleteById(id);
    }
}
