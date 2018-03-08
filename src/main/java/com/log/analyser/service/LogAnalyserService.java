package com.log.analyser.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.log.analyser.dto.ListResultDTO;
import com.log.analyser.model.IpsBloquedsModel;
import com.log.analyser.repository.IpsBloquedsRepository;
import com.log.analyser.repository.LogAnalyserCustomRepository;

@Service
public class LogAnalyserService {
	
	private static final Logger log = LoggerFactory.getLogger(LogAnalyserService.class);
	
	@Autowired
	private	LogAnalyserCustomRepository customRepository;
	
	@Autowired
	private IpsBloquedsRepository bloquedsRepository;
	
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public StringBuilder validateParam(String startDate, String duration, int threshold) {

		StringBuilder sb = new StringBuilder();
		
		try {
			sf.parse(startDate);
		} catch (Exception e) {
			sb.append("data invalid="+startDate);
			log.error("data inválida!", e);
		}
		
		if (!("hourly".equals(duration) || "daily".equals(duration))) {
			sb.append(" duration invalid="+duration);
		}
		
		if (threshold < 0) {
			sb.append(" threshold invalid="+duration);
		}
		
		return sb;
		
	}

	@SuppressWarnings("unchecked")
	public List<ListResultDTO> getIpsByThreshold(String startDate, String duration, int threshold) {

		try {
			
			Date dateIn = sf.parse(startDate);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateIn);
			
			if ("hourly".equals(duration)) {
				cal.add(Calendar.HOUR, 1);
			}
			
			if ("daily".equals(duration)) {
				cal.add(Calendar.HOUR, 24);
			}
			
			Date dateEnd = cal.getTime();
			
			List<ListResultDTO> list = customRepository.findListByParamns(dateIn, dateEnd, threshold);
			
			List<ListResultDTO> ipsBloqueds = list.stream()
					.filter(ListResultDTO::isBloqued)
					.collect(Collectors.toList());
			
			ipsBloqueds.forEach(ip -> {
				
				
				IpsBloquedsModel findByIp = bloquedsRepository.findByIp(ip.getIp());
				
				if (findByIp == null) {
					
					IpsBloquedsModel ipsBloquedsModel = new IpsBloquedsModel();
					ipsBloquedsModel.setIp(ip.getIp());
					ipsBloquedsModel.setDescription("IP blocked because it exceeded "+ threshold +" request");
					
					bloquedsRepository.save(ipsBloquedsModel);
				} else {
					findByIp.setDescription("IP blocked because it exceeded "+ threshold +" request");
					bloquedsRepository.save(findByIp);
				}
				
				
			});
			
			return ipsBloqueds;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return Collections.EMPTY_LIST;
	}
	
	
	
	public List<IpsBloquedsModel> findAllIpsBloqueds () {
		return bloquedsRepository.findAll();
	}
	
}
