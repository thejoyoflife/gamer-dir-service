package com.example.gamer.directory.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.gamer.directory.domain.dtos.GamerSearchRequestDTO;
import com.example.gamer.directory.domain.entities.Gamer;

public interface GamerRepositoryCustom {
	public Page<Gamer> searchGamers(GamerSearchRequestDTO searchCriteria, Pageable pageable);
}
