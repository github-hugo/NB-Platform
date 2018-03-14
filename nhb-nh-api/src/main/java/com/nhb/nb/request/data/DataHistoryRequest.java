package com.nhb.nb.request.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nhb.nb.request.common.PageRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DataHistoryRequest extends PageRequest {

	/**
	 * 租户id
	 */
	@XmlElement(name = "tenantId")
	@ApiModelProperty(value = "tenantId", required = true, example = "Suhisdasdmicrvrm")
	private String tenantId;

	/**
	 * 设备id
	 */
	@XmlElement(name = "circuitId")
	@ApiModelProperty(value = "circuitId", required = true, example = "1008")
	private String circuitId;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(String circuitId) {
		this.circuitId = circuitId;
	}

	

}
