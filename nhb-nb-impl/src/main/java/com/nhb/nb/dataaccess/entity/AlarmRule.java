package com.nhb.nb.dataaccess.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="alarm_rule")
public class AlarmRule {
	
	private String deviceId;
	
	private String voltHi;
	
	private String voltLo;
	
	private String currHi;
	
	private String currLo;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getVoltHi() {
		return voltHi;
	}

	public void setVoltHi(String voltHi) {
		this.voltHi = voltHi;
	}

	public String getVoltLo() {
		return voltLo;
	}

	public void setVoltLo(String voltLo) {
		this.voltLo = voltLo;
	}

	public String getCurrHi() {
		return currHi;
	}

	public void setCurrHi(String currHi) {
		this.currHi = currHi;
	}

	public String getCurrLo() {
		return currLo;
	}

	public void setCurrLo(String currLo) {
		this.currLo = currLo;
	}
		
	
}
