package com.nhb.nb.dataaccess.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "sys_area")
public class SysArea {

	@Id
	@Field("area_id")
	private String areaId;

	/**
	 * 父节点id
	 */
	@Field("parent_id")
	private String parentId;
	
	/**
	 * 顶级节点id
	 */
	@Field("top_area_id")
	private String topAreaId;

	@Field("area_name")
	private String areaName;
	
	@Field("area_type")
	private String areaType;

	/**
	 * 租户id
	 */
	@Field("tenant_id")
	private String tenantId;

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
