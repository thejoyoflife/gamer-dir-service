package com.example.gamer.directory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gamer.directory.domain.entities.Gamer;

@Repository
public interface GamerRepository extends JpaRepository<Gamer, Long>, GamerRepositoryCustom {
	
}
