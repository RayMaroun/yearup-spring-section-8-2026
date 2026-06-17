package com.pluralsight.gamelibrary.data;

import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.models.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findById_AfterSave_ReturnsTheStoredGame() {
        // Arrange - persist a genre, then a game that belongs to it
        Genre rpg = entityManager.persistAndFlush(new Genre("RPG"));
        Game saved = entityManager.persistAndFlush(new Game("Elden Ring", 2022, 9.6, rpg));

        // Act
        Game found = gameRepository.findById(saved.getId()).orElseThrow();

        // Assert
        assertEquals("Elden Ring", found.getTitle());
        assertEquals(2022, found.getReleaseYear());
        assertEquals(9.6, found.getRating(), 0.001);
    }

    @Test
    void findById_SavedGameWithGenre_KeepsTheRelationship() {
        // Arrange
        Genre indie = entityManager.persistAndFlush(new Genre("Indie"));
        Game saved = entityManager.persistAndFlush(new Game("Celeste", 2018, 9.1, indie));

        // Act
        Game found = gameRepository.findById(saved.getId()).orElseThrow();

        // Assert
        assertEquals("Indie", found.getGenre().getName());
    }
}
