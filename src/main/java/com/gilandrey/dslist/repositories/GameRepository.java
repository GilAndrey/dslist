package com.gilandrey.dslist.repositories;

import com.gilandrey.dslist.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
