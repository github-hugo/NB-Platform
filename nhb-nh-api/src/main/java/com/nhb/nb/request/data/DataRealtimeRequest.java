package com.nhb.nb.request.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DataRealtimeRequest {

	@XmlElement(name = "tenantId")
	@ApiModelProperty(value = "tenantId", required = true, example = "Suhisdasdmicrvrm")
	private String tenantId;

	@XmlElement(name = "circuitIds")
	@ApiModelProperty(value = "circuitIds", required = true, example = "['123','122']")
	private List<String> circuitIds;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public List<String> getCircuitIds() {
		return circuitIds;
	}

	public void setCircuitIds(List<String> circuitIds) {
		this.circuitIds = circuitIds;
	}


}
