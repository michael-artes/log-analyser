package com.log.analyser.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.log.analyser.model.LogAnalyserModel;

@Repository
public class LogAnalyserImplDao implements LogAnalyserRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<LogAnalyserModel> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LogAnalyserModel> cq = cb.createQuery(LogAnalyserModel.class);
        Root<LogAnalyserModel> rootEntry = cq.from(LogAnalyserModel.class);
        CriteriaQuery<LogAnalyserModel> all = cq.select(rootEntry);
        TypedQuery<LogAnalyserModel> allQuery = em.createQuery(all);
        return allQuery.getResultList();
	}

}
