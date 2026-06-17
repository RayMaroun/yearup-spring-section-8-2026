package com.pluralsight.gamelibrary.data;

import com.pluralsight.gamelibrary.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
