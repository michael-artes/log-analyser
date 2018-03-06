package com.log.analyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.log.analyser.component.ProcessorByArguments;

@SpringBootApplication
public class LogAnalyserApplication {

	public static void main(String[] args) {
		System.out.println("start inicio: " + System.currentTimeMillis());
		ConfigurableApplicationContext run = SpringApplication.run(LogAnalyserApplication.class, args);
		run.getBean(ProcessorByArguments.class).process(args);
	}
	
}
