package com.log.analyser.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_log_analyser")
public class LogAnalyserModel {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(name="data_access")
	private Date dataAccess;

	@Column(name="ip")
	private String ip;

	@Column(name="request")
	private String request;
	
	@Column(name="status")
	private int status;
	
	@Column(name="user_agent")
	private String userAgent;
	
	public LogAnalyserModel() {}
	
	public LogAnalyserModel(Date dataAccess, String ip, String request, int status, String userAgent) {
		super();
		this.dataAccess = dataAccess;
		this.ip = ip;
		this.request = request;
		this.status = status;
		this.userAgent = userAgent;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(Date dataAccess) {
		this.dataAccess = dataAccess;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAccess == null) ? 0 : dataAccess.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		result = prime * result + status;
		result = prime * result + ((userAgent == null) ? 0 : userAgent.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogAnalyserModel other = (LogAnalyserModel) obj;
		if (dataAccess == null) {
			if (other.dataAccess != null)
				return false;
		} else if (!dataAccess.equals(other.dataAccess))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		if (status != other.status)
			return false;
		if (userAgent == null) {
			if (other.userAgent != null)
				return false;
		} else if (!userAgent.equals(other.userAgent))
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return "LogAnalyserModel [date=" + dataAccess + ", ip=" + ip + ", request=" + request + ", status=" + status
				+ ", userAgent=" + userAgent + "]";
	}

}
