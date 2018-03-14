package com.nhb.nb.dataaccess.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "device_circuit")
public class DeviceCircuit {

	@Id
	@Field("circuitId")
	private String circuitId;

	@Field("circuit_no")
	private String circuitNo;

	@Field("device_id")
	private String deviceId;

	@Field("name")
	private String name;

	public String getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(String circuitId) {
		this.circuitId = circuitId;
	}

	public String getCircuitNo() {
		return circuitNo;
	}

	public void setCircuitNo(String circuitNo) {
		this.circuitNo = circuitNo;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
