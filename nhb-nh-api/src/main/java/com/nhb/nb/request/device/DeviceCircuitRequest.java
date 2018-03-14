package com.nhb.nb.request.device;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceCircuitRequest {
	
	@XmlElement(name="circuitId")
	@ApiModelProperty(value="circuitId",required=true,example="deviceId-circuitNo")
	private String circuitId;

	@XmlElement(name="circuitNo")
	@ApiModelProperty(value="circuitNo",required=true,example="circuitNo")
	private String circuitNo;

	@XmlElement(name="deviceId")
	@ApiModelProperty(value="deviceId",required=true,example="UUID-87888889sd21")
	private String deviceId;

	@XmlElement(name="name")
	@ApiModelProperty(value="name",required=true,example="回路名称")
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
