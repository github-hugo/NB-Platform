package com.nhb.nb.dataaccess.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "unit_rate")
public class UnitRate {

	@Id
	private String id;

	@Field("area_id")
	private String areaId;

	@Field("electricity")
	private Double electricity;

	@Field("water")
	private Double water;

	@Field("gas")
	private Double gas;

	@Field("heat")
	private Double heat;

	@Field("start_date")
	private String startDate;

	@Field("end_date")
	private String endDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Double getElectricity() {
		return electricity;
	}

	public void setElectricity(Double electricity) {
		this.electricity = electricity;
	}

	public Double getWater() {
		return water;
	}

	public void setWater(Double water) {
		this.water = water;
	}

	public Double getGas() {
		return gas;
	}

	public void setGas(Double gas) {
		this.gas = gas;
	}

	public Double getHeat() {
		return heat;
	}

	public void setHeat(Double heat) {
		this.heat = heat;
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

}
