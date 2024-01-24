package com.example.gamer.directory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gamer.directory.domain.entities.Credit;
import com.example.gamer.directory.domain.entities.CreditId;

@Repository
public interface CreditRepository extends JpaRepository<Credit, CreditId>{
	@Query(value = """
			select case when count(*) > 0 then true else false end 
			from credit c where c.gamer_id = :gamerId and c.game_id = :gameId
			""", nativeQuery = true)
	public boolean isCreditAlreadyAwarded(@Param("gamerId") Long gamerId, 
											@Param("gameId") Integer gameId);
}
