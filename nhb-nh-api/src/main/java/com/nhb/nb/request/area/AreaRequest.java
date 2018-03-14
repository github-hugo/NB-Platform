package com.nhb.nb.request.area;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AreaRequest {

	@XmlElement(name = "areaId")
	@ApiModelProperty(value = "areaId", required = true, example = "1008")
	private String areaId;

	@XmlElement(name = "parentId")
	@ApiModelProperty(value = "parentId", required = true, example = "100")
	private String parentId;
	
	@XmlElement(name = "topAreaId")
	@ApiModelProperty(value = "topAreaId", required = true, example = "100901")
	private String topAreaId;

	@XmlElement(name = "areaName")
	@ApiModelProperty(value = "areaName", required = true, example = "园区电信基站")
	private String areaName;
	
	@XmlElement(name = "areaType")
	@ApiModelProperty(value = "areaType", required = true, example = "device")
	private String areaType;

	@XmlElement(name = "tenantId")
	@ApiModelProperty(value = "tenantId", required = true, example = "T001")
	private String tenantId;

//	@XmlElement(name = "pageNo")
//	@ApiModelProperty(value = "pageNo", required = true, example = "1")
//	private Integer pageNo;
//
//	@XmlElement(name = "pageSize")
//	@ApiModelProperty(value = "pageSize", required = true, example = "10")
//	private Integer pageSize;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTopAreaId() {
		return topAreaId;
	}

	public void setTopAreaId(String topAreaId) {
		this.topAreaId = topAreaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	

}
