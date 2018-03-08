package com.log.analyser.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.log.analyser.configuration.listener.JobCompletionNotificationListener;
import com.log.analyser.dto.LogAnalyserDTO;
import com.log.analyser.model.LogAnalyserModel;
import com.log.analyser.processor.LogAnalyserProcessor;

@Configuration
@EnableBatchProcessing
public class LogConfiguration {
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;
    
    @Bean
    public LogAnalyserProcessor processor() {
        return new LogAnalyserProcessor();
    }
    
    @Bean
    public FlatFileItemReader<LogAnalyserDTO> reader() {
    	
    	DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer("|");
    	tokenizer.setNames(new String[] { "dataAccess", "ip", "request", "status", "userAgent" });
    	
    	BeanWrapperFieldSetMapper<LogAnalyserDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<LogAnalyserDTO>();
    	fieldSetMapper.setTargetType(LogAnalyserDTO.class);
    	
    	DefaultLineMapper<LogAnalyserDTO> lineMapper = new DefaultLineMapper<LogAnalyserDTO>();
    	lineMapper.setLineTokenizer(tokenizer);
    	lineMapper.setFieldSetMapper(fieldSetMapper);
    	
    	
        FlatFileItemReader<LogAnalyserDTO> reader = new FlatFileItemReader<LogAnalyserDTO>();
        reader.setResource(new ClassPathResource("access.log"));
		reader.setLineMapper(lineMapper);
		
        return reader;
    }

    
    @Bean
    public JdbcBatchItemWriter<LogAnalyserModel> writer() {
        JdbcBatchItemWriter<LogAnalyserModel> writer = new JdbcBatchItemWriter<LogAnalyserModel>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<LogAnalyserModel>());
        writer.setSql("INSERT INTO tb_log_analyser (data_access, ip, request, status, user_agent) VALUES (:dataAccess, :ip, :request, :status, :userAgent)");
        writer.setDataSource(dataSource);
        return writer;
    }
    
    
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }
    

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<LogAnalyserDTO, LogAnalyserModel> chunk(500)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    
    
}
