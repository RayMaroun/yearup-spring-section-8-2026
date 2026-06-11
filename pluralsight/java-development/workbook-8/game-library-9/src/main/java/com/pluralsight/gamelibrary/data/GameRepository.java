package com.pluralsight.gamelibrary.data;

import com.pluralsight.gamelibrary.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByReleaseYear(int year);

    List<Game> findByTitleContaining(String text);

    List<Game> findByRatingGreaterThan(double rating);

    @Query("SELECT g FROM Game g WHERE g.rating >= :minRating AND g.releaseYear >= :minYear")
    List<Game> search(@Param("minRating") double minRating, @Param("minYear") int minYear);

    // Module 4: traverse the relationship - find games whose genre's name matches.
    List<Game> findByGenre_Name(String name);
}
