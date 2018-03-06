package com.log.analyser.repository;

import java.util.List;

import com.log.analyser.model.LogAnalyserModel;

public interface LogAnalyserRepository {
	
	List<LogAnalyserModel> findAll();	
	
}