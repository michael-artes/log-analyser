package com.log.analyser.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.log.analyser.dto.LogAnalyserDTO;
import com.log.analyser.model.LogAnalyserModel;

public class LogAnalyserProcessor implements ItemProcessor<LogAnalyserDTO, LogAnalyserModel> {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    
	@Override
	public LogAnalyserModel process(LogAnalyserDTO logAnalyserDTO) throws Exception {
		
		Date date = sf.parse(logAnalyserDTO.getDataAccess());
		
		LogAnalyserModel l = new LogAnalyserModel(
					date, 
					logAnalyserDTO.getIp(), 
					logAnalyserDTO.getRequest(), 
					Integer.valueOf(logAnalyserDTO.getStatus()), 
					logAnalyserDTO.getUserAgent()
				);

		return l;
	}

}
