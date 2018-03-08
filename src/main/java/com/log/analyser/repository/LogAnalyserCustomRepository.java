package com.log.analyser.repository;

import java.util.Date;
import java.util.List;

import com.log.analyser.dto.ListResultDTO;

public interface LogAnalyserCustomRepository {
	List<ListResultDTO> findListByParamns(Date dateIn, Date dateEnd, int threshold);
}
