package com.nhb.nb.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("energy")
public class ExportEnergyDTO {
	
	@Excel(name = "areaId")
	private String areaId;
	
	@Excel(name = "areaName")
	private String areaName;
	
	@Excel(name = "energy")
	private String energy;
	
	@Excel(name = "money")
	private String money;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String energy) {
		this.energy = energy;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	
	
	
}	
