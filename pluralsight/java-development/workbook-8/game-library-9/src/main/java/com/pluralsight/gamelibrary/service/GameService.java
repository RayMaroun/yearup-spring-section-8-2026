package com.pluralsight.gamelibrary.service;

import com.pluralsight.gamelibrary.data.GameRepository;
import com.pluralsight.gamelibrary.data.GenreRepository;
import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public GameService(GameRepository gameRepository, GenreRepository genreRepository) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
    }

    public long count() {
        return gameRepository.count();
    }

    public List<Game> allGames() {
        return gameRepository.findAll();
    }

    public List<Genre> allGenres() {
        return genreRepository.findAll();
    }

    public List<Game> byYear(int year) {
        return gameRepository.findByReleaseYear(year);
    }

    public List<Game> byTitle(String text) {
        return gameRepository.findByTitleContaining(text);
    }

    public List<Game> byMinRating(double rating) {
        return gameRepository.findByRatingGreaterThan(rating);
    }

    public List<Game> search(double minRating, int minYear) {
        return gameRepository.search(minRating, minYear);
    }

    public List<Game> byGenre(String genreName) {
        return gameRepository.findByGenre_Name(genreName);
    }

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

    // Puts starting data in an empty database. The count check makes it
    // idempotent. A fresh DB (after the Module 4 reset) gets genres and games
    // that are already linked, since every game requires a genre.
    public void seedIfEmpty() {
        if (gameRepository.count() > 0) {
            return;
        }
        Genre action = genreRepository.save(new Genre("Action"));
        Genre rpg = genreRepository.save(new Genre("RPG"));
        Genre indie = genreRepository.save(new Genre("Indie"));
        Genre shooter = genreRepository.save(new Genre("Shooter"));
        Genre adventure = genreRepository.save(new Genre("Adventure"));

        gameRepository.save(new Game("Hollow Knight", 2017, 9.4, indie));
        gameRepository.save(new Game("Elden Ring", 2022, 9.6, rpg));
        gameRepository.save(new Game("Celeste", 2018, 9.1, indie));
        gameRepository.save(new Game("DOOM Eternal", 2020, 8.9, shooter));
        gameRepository.save(new Game("Stardew Valley", 2016, 9.2, indie));
        gameRepository.save(new Game("Hades", 2020, 9.5, action));
        gameRepository.save(new Game("The Witcher 3", 2015, 9.8, rpg));
        gameRepository.save(new Game("Cuphead", 2017, 8.8, adventure));
    }
}
