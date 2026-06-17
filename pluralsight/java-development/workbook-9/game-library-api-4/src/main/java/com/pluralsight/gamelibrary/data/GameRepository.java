package com.pluralsight.gamelibrary.data;

import com.pluralsight.gamelibrary.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
