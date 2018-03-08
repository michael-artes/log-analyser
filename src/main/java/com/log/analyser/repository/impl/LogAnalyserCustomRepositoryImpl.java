package com.log.analyser.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.log.analyser.dto.ListResultDTO;
import com.log.analyser.repository.LogAnalyserCustomRepository;

@Repository
public class LogAnalyserCustomRepositoryImpl implements LogAnalyserCustomRepository{

	@PersistenceContext
    private EntityManager entityManager;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<ListResultDTO> findListByParamns(Date dateIn, Date dateEnd, int threshold) {

		Query query = entityManager.createQuery("SELECT " + 
				"	DISTINCT ip, " + 
				"	(CASE WHEN count(*) > :threshold THEN 'true' ELSE 'false' END) as isBloqued" + 
				"	FROM LogAnalyserModel " + 
				"	WHERE dataAccess BETWEEN :dateIn and :dateEnd " + 
				"	GROUP BY ip");
		
		query.setParameter("dateIn", dateIn);
		query.setParameter("dateEnd", dateEnd);
		query.setParameter("threshold", Long.parseLong(""+threshold));
		
		List resultList = query.getResultList();
		
		return getResultTransform(resultList);
	}

	
	@SuppressWarnings("rawtypes")
	private List<ListResultDTO> getResultTransform(List resultList) {

		List<ListResultDTO> list = new ArrayList<>();
		
		if (resultList != null && !resultList.isEmpty()) {
			
			for (Object object : resultList) {
				Object[] obj = (Object[]) object;

				
				
				ListResultDTO listResultDTO = new ListResultDTO(
							(String) obj[0], 
							Boolean.parseBoolean((String) obj[1])
						);
				
				list.add(listResultDTO);
			}
		}
		
		return list;
	}

}
