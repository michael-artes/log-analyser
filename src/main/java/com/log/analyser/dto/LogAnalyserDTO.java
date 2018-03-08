package com.log.analyser.dto;

public class LogAnalyserDTO {
	
	private String dataAccess;
	private String ip;
	private String request;
	private String status;
	private String userAgent;
	
	
	public String getDataAccess() {
		return dataAccess;
	}
	public void setDataAccess(String dataAccess) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	
	@Override
	public String toString() {
		return "LogAnalyserDTO [dataAccess=" + dataAccess + ", ip=" + ip + ", request=" + request + ", status=" + status
				+ ", userAgent=" + userAgent + "]";
	}
}
