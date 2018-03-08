package com.log.analyser;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.log.analyser.dto.ListResultDTO;
import com.log.analyser.service.LogAnalyserService;

@SpringBootApplication
public class LogAnalyserApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext run = SpringApplication.run(LogAnalyserApplication.class, args);
		
		String startDate = null;
		String duration = null;
		int threshold = -1;
		
		for (String string : args) {
			
			String[] split = string.split("=");
			
			if ("--startDate".equals(split[0])) {
				startDate = split[1];
			}
			
			if ("--duration".equals(split[0])) {
				duration = split[1];
			}
			
			if ("--threshold".equals(split[0])) {
				try {
					threshold = Integer.parseInt(split[1]);
				} catch (Exception e) {
				}
			}
		}

		LogAnalyserService service = run.getBean(LogAnalyserService.class);
		StringBuilder validateParam = service.validateParam(startDate, duration, threshold);
		
		if (!validateParam.toString().isEmpty()) {
			System.err.println(validateParam.toString());
		} else {
			List<ListResultDTO> ipsByThreshold = service.getIpsByThreshold(startDate, duration, threshold);
			
			System.out.println("list of ips found - size: " + ipsByThreshold.size());
			ipsByThreshold.forEach(System.out::println);
		}
		
	}
	
}
