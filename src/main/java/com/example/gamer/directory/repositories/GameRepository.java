package com.example.gamer.directory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gamer.directory.domain.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}
