package com.nhb.nb.request.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel
public class EnergyDataRequest {

	@XmlElement(name = "tenantId")
	@ApiModelProperty(value = "tenantId", required = true, example = "267dce1a-6d63-4bbc-a289-1c3a7acf6e2f")
	private String tenantId;

	@XmlElement(name = "date")
	@ApiModelProperty(value = "date", required = true, example = "2017-11-09")
	private String date;

	@XmlElement(name = "startDate")
	@ApiModelProperty(value = "startDate", required = true, example = "2017-11-09")
	private String startDate;

	@XmlElement(name = "endDate")
	@ApiModelProperty(value = "endDate", required = true, example = "2018-11-09")
	private String endDate;

	@XmlElement(name = "circuitIds")
	@ApiModelProperty(value = "circuitIds", required = true, example = "[2,1,3]")
	private List<String> circuitIds;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<String> getCircuitIds() {
		return circuitIds;
	}

	public void setCircuitIds(List<String> circuitIds) {
		this.circuitIds = circuitIds;
	}

}
