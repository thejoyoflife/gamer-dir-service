package com.example.gamer.directory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gamer.directory.domain.entities.Credit;
import com.example.gamer.directory.domain.entities.CreditId;

@Repository
public interface CreditRepository extends JpaRepository<Credit, CreditId>, CreditRepositoryCustom {
}
