package com.example.gamer.directory.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gamer.directory.domain.dtos.GamerDTO;
import com.example.gamer.directory.domain.dtos.GamerEnrollmentDTO;
import com.example.gamer.directory.domain.dtos.GamerSearchRequestDTO;
import com.example.gamer.directory.domain.entities.Gamer;
import com.example.gamer.directory.domain.mappers.GameMapper;
import com.example.gamer.directory.exceptions.NotFoundException;
import com.example.gamer.directory.repositories.GamerRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GamerService {
	
	private final GameMapper gameMapper;
	private final GamerRepository gamerRepository;
	
	@Transactional
	public Long enroll(GamerEnrollmentDTO enrollment) {
		Gamer gamer = gameMapper.toGamerFromEnrollmentDTO(enrollment);
		var saved = gamerRepository.save(gamer);
		return saved.getId();
	}
	
	public GamerDTO findGamerById(Long id) {
		var gamer = gamerRepository.findById(id)
						.orElseThrow(() -> 
							new NotFoundException("Gamer not found for id: %d".formatted(id)));
		return gameMapper.toGamerDTO(gamer);
	}
	
	public Page<GamerDTO> findGamers(GamerSearchRequestDTO criteria, int pageOffset, int pageSize) {		
		var gamers = gamerRepository.searchGamers(criteria, PageRequest.of(pageOffset, pageSize));
		return gamers
					.map(gamer -> gameMapper.toGamerDTO(gamer));
	}
}
