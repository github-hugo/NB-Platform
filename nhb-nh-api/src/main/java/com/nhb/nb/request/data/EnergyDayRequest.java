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
public class EnergyDayRequest {

	@XmlElement(name = "areaIds")
	@ApiModelProperty(value = "areaIds", required = true, example = "1008")
	private List<String> areaIds;

	@XmlElement(name = "startDate")
	@ApiModelProperty(value = "startDate", required = true, example = "2018-01-01")
	private String startDate;

	@XmlElement(name = "endDate")
	@ApiModelProperty(value = "endDate", required = true, example = "2018-03-08")
	private String endDate;

	@XmlElement(name = "areaId")
	@ApiModelProperty(value = "areaId", required = true, example = "1008")
	private String areaId;

	@XmlElement(name = "months")
	@ApiModelProperty(value = "months", required = true, example = "2017-03,2018-03")
	private List<String> months;

	public List<String> getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(List<String> areaIds) {
		this.areaIds = areaIds;
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

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public List<String> getMonths() {
		return months;
	}

	public void setMonths(List<String> months) {
		this.months = months;
	}

}
