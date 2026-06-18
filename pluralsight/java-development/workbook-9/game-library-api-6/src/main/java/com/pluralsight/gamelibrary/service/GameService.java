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

    public Game byId(long id) {
        return gameRepository.findById(id).orElse(null);
    }

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

    public Game createGame(Game game) {
        game.setId(null);                       // a new row, never an update
        game.setGenre(resolveGenre(game));
        return gameRepository.save(game);
    }

    public Game updateGame(long id, Game updated) {
        Game existing = byId(id);
        if (existing == null) return null;      // controller turns this into a 404
        existing.setTitle(updated.getTitle());
        existing.setReleaseYear(updated.getReleaseYear());
        existing.setRating(updated.getRating());
        existing.setGenre(resolveGenre(updated));
        return gameRepository.save(existing);
    }

    public void deleteGame(long id) {
        gameRepository.deleteById(id);
    }

    // The request body carries the genre's id; load the real, managed genre.
    private Genre resolveGenre(Game game) {
        if (game.getGenre() == null || game.getGenre().getId() == null) return null;
        return genreRepository.findById(game.getGenre().getId()).orElse(null);
    }
}
