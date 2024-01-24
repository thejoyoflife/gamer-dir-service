package com.example.gamer.directory.repositories;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.gamer.directory.domain.InterestLevel;
import com.example.gamer.directory.domain.dtos.GamerSearchRequestDTO;
import com.example.gamer.directory.domain.entities.Game;
import com.example.gamer.directory.domain.entities.Game_;
import com.example.gamer.directory.domain.entities.Gamer;
import com.example.gamer.directory.domain.entities.Gamer_;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.MapJoin;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class GamerRepositoryCustomImpl implements GamerRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Gamer> searchGamers(GamerSearchRequestDTO searchCriteria, Pageable pageable) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Gamer> query = cb.createQuery(Gamer.class);
		Root<Gamer> root = query.from(Gamer.class);
		
		Predicate predicate = buildPredicate(searchCriteria, cb, query, root);
		query.where(predicate);
		
		TypedQuery<Gamer> typedQuery = entityManager.createQuery(query);
		typedQuery.setFirstResult((int) pageable.getOffset());
		typedQuery.setMaxResults(pageable.getPageSize());
		
		List<Gamer> resulEntitytList = typedQuery.getResultList();
		
		Long total = countGamersBasedOnCriteria(searchCriteria);
		
		return new PageImpl<>(resulEntitytList, pageable, total);
	}
	
	private Long countGamersBasedOnCriteria(GamerSearchRequestDTO criteria) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Gamer> root = query.from(Gamer.class);
		
		Predicate predicate = buildPredicate(criteria, cb, query, root);
		query.select(cb.count(root));
		query.where(predicate);
		
		Long total = entityManager.createQuery(query).getSingleResult();
		return total;
	}
	
	private Predicate buildPredicate(GamerSearchRequestDTO criteria, CriteriaBuilder cb, 
											CriteriaQuery<?> cq, Root<Gamer> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (criteria.geography() != null) {
			predicates.add(cb.equal(root.get(Gamer_.GEOGRAPHY), criteria.geography()));
		}
		
		if (isGameInterestCriteriaAvailable(criteria)) {
			MapJoin<Gamer, Game, InterestLevel> gameJoin = root.joinMap(Gamer_.INTERESTS, JoinType.INNER);
			
			if (isNotBlank(criteria.gameName())) {
				predicates.add(cb.equal(gameJoin.key().get(Game_.NAME), criteria.gameName()));
			}
			if (criteria.level() != null) {
				predicates.add(cb.equal(gameJoin.value(), criteria.level()));
			}
		}
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
	
	private boolean isGameInterestCriteriaAvailable(GamerSearchRequestDTO criteria) {
		return isNotBlank(criteria.gameName())
				|| criteria.level() != null;
	}
}
