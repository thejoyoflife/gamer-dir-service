package com.example.gamer.directory.repositories;

import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.MAX_CREDIT_CALCULATION_QUERY;

import java.util.List;

import org.hibernate.query.NativeQuery;

import com.example.gamer.directory.domain.dtos.MaxCreditGameDTO;
import com.example.gamer.directory.repositories.extras.MaxCreditGameDTOResultTransformer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CreditRepositoryCustomImpl implements CreditRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<MaxCreditGameDTO> gamesWithMaxCredit() {
		return entityManager.createNativeQuery(MAX_CREDIT_CALCULATION_QUERY)
					.unwrap(NativeQuery.class)
					.setTupleTransformer(new MaxCreditGameDTOResultTransformer())
					.getResultList();
	}
}
