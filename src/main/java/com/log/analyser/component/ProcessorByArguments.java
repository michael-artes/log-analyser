package com.log.analyser.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.log.analyser.model.LogAnalyserModel;
import com.log.analyser.repository.LogAnalyserRepository;

@Component
public class ProcessorByArguments {

	@Autowired
	private LogAnalyserRepository logAnalyserRepository;
	
	
	public void process(String[] args) {	
		System.out.println("pega a visao: " + args);
		List<LogAnalyserModel> findAll = logAnalyserRepository.findAll();
		findAll.forEach(System.out::println);
	} 
}
