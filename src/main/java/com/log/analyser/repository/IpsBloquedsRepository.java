package com.log.analyser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log.analyser.model.IpsBloquedsModel;

public interface IpsBloquedsRepository extends JpaRepository<IpsBloquedsModel, Integer>{
	
	IpsBloquedsModel findByIp(String ip);

}
