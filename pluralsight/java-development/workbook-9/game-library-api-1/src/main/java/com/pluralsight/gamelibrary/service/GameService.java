package com.pluralsight.gamelibrary.service;

import com.pluralsight.gamelibrary.data.GameRepository;
import com.pluralsight.gamelibrary.data.GenreRepository;
import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.models.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;

    public GameService(GameRepository gameRepository, GenreRepository genreRepository) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
    }

    public List<Game> allGames() { return gameRepository.findAll(); }

    public List<Game> byYear(int year) { return gameRepository.findByReleaseYear(year); }
    public List<Game> byTitle(String text) { return gameRepository.findByTitleContaining(text); }
    public List<Game> byMinRating(double rating) { return gameRepository.findByRatingGreaterThan(rating); }
    public List<Game> search(double minRating, int minYear) { return gameRepository.search(minRating, minYear); }
    public List<Game> byGenre(String genreName) { return gameRepository.findByGenre_Name(genreName); }

    public Game byId(long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No game with id " + id));
    }

    public Game addGame(String title, int year, double rating, long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new NotFoundException("No genre with id " + genreId));
        return gameRepository.save(new Game(title, year, rating, genre));
    }

    public Game updateRating(long id, double rating) {
        Game game = byId(id);
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
