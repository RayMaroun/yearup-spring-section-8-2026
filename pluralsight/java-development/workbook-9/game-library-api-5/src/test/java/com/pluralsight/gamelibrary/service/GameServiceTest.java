package com.pluralsight.gamelibrary.service;

import com.pluralsight.gamelibrary.data.GameRepository;
import com.pluralsight.gamelibrary.data.GenreRepository;
import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.models.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    void byId_MissingId_ReturnsNull() {
        // Arrange - script the mock: asked for id 99, hand back empty
        when(gameRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Game found = gameService.byId(99L);

        // Assert
        assertNull(found);
    }

    @Test
    void byId_ExistingId_ReturnsTheGame() {
        // Arrange
        Game game = new Game("Elden Ring", 2022, 9.6, new Genre("RPG"));
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        // Act
        Game found = gameService.byId(1L);

        // Assert
        assertNotNull(found);
        assertEquals("Elden Ring", found.getTitle());
        assertEquals(2022, found.getReleaseYear());
    }

    @Test
    void createGame_NewGame_ResolvesGenreAndSaves() {
        // Arrange
        Genre rpg = new Genre("RPG");
        rpg.setId(2L);
        Game incoming = new Game("New Game", 2024, 8.0, rpg);
        when(genreRepository.findById(2L)).thenReturn(Optional.of(rpg));
        when(gameRepository.save(any(Game.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        Game saved = gameService.createGame(incoming);

        // Assert
        assertEquals("RPG", saved.getGenre().getName());
        verify(gameRepository).save(incoming);
    }

    @Test
    void search_MinRatingFilter_ReturnsOnlyHigherRated() {
        // Arrange
        Genre rpg = new Genre("RPG");
        Game high = new Game("Elden Ring", 2022, 9.6, rpg);
        Game low = new Game("Old Game", 2010, 7.0, rpg);
        when(gameRepository.findAll()).thenReturn(List.of(high, low));

        // Act
        List<Game> found = gameService.search(null, null, 9.0, null);

        // Assert
        assertEquals(1, found.size());
        assertEquals("Elden Ring", found.get(0).getTitle());
    }
}
