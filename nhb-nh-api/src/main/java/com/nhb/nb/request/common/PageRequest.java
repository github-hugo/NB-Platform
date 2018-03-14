package com.nhb.nb.request.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel
public class PageRequest {

	@XmlElement(name = "pageNo")
	@ApiModelProperty(value = "pageNo", required = true, example = "1")
	private Integer pageNo;

	@XmlElement(name = "pageSize")
	@ApiModelProperty(value = "pageSize", required = true, example = "10")
	private Integer pageSize;

	@XmlElement(name = "startTime")
	@ApiModelProperty(value = "startTime", required = true, example = "2017-01-01 00:00:00")
	private String startTime;

	@XmlElement(name = "endTime")
	@ApiModelProperty(value = "endTime", required = true, example = "2018-07-19 00:00:00")
	private String endTime;

	public PageRequest() {
		super();
	}

	public PageRequest(Integer pageNo, Integer pageSize) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
