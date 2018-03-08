package com.log.analyser.dto;

public class ListResultDTO {
	
	private String ip;
	private boolean isBloqued;
	
	public ListResultDTO(String ip, boolean isBloqued) {
		this.ip = ip;
		this.isBloqued = isBloqued;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public boolean isBloqued() {
		return isBloqued;
	}
	public void setBloqued(boolean isBloqued) {
		this.isBloqued = isBloqued;
	}

	@Override
	public String toString() {
		return "ListResultDTO [ip=" + ip + ", isBloqued=" + isBloqued + "]";
	}
	

}
