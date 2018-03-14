package com.nhb.nb.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class DataElectricityDTO {
	
	@Excel(name = "设备Id")
	private String deviceId;
	
	@Excel(name = "设备名称")
	private String deviceName;
	
	@Excel(name = "回路名称")
	private String circuitName;

	@Excel(name = "时间")
	private String meterTime;
	
	@Excel(name = "电压变比")
	private String voltChange;
	
	@Excel(name = "电流变比")
	private String currentChange;
	
	@Excel(name = "频率")
	private String frequency;
	
	@Excel(name = "A相电压")
	private String aVolt;
	
	@Excel(name = "B相电压")
	private String bVolt;
	
	@Excel(name = "C相电压")
	private String cVolt;
	
	@Excel(name = "A相电流")
	private String aCurrent;
	
	@Excel(name = "B相电流")
	private String bCurrent;
	
	@Excel(name = "C相电流")
	private String cCurrent;
	
	@Excel(name = "功率")
	private String kw;

	@Excel(name = "A相功率")
	private String aKw;

	@Excel(name = "B相功率")
	private String bKw;

	@Excel(name = "C相功率")
	private String cKw;

	@Excel(name = "总电能")
	private String kwhTotal;

	@Excel(name = "A相电能")
	private String aKwh;
	
	@Excel(name = "B相电能")
	private String bKwh;
	
	@Excel(name = "C相电能")
	private String cKwh;

	public String getMeterTime() {
		return meterTime;
	}

	public void setMeterTime(String meterTime) {
		this.meterTime = meterTime;
	}

	public String getVoltChange() {
		return voltChange;
	}

	public void setVoltChange(String voltChange) {
		this.voltChange = voltChange;
	}

	public String getCurrentChange() {
		return currentChange;
	}

	public void setCurrentChange(String currentChange) {
		this.currentChange = currentChange;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getaVolt() {
		return aVolt;
	}

	public void setaVolt(String aVolt) {
		this.aVolt = aVolt;
	}

	public String getbVolt() {
		return bVolt;
	}

	public void setbVolt(String bVolt) {
		this.bVolt = bVolt;
	}

	public String getcVolt() {
		return cVolt;
	}

	public void setcVolt(String cVolt) {
		this.cVolt = cVolt;
	}

	public String getaCurrent() {
		return aCurrent;
	}

	public void setaCurrent(String aCurrent) {
		this.aCurrent = aCurrent;
	}

	public String getbCurrent() {
		return bCurrent;
	}

	public void setbCurrent(String bCurrent) {
		this.bCurrent = bCurrent;
	}

	public String getcCurrent() {
		return cCurrent;
	}

	public void setcCurrent(String cCurrent) {
		this.cCurrent = cCurrent;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public String getaKw() {
		return aKw;
	}

	public void setaKw(String aKw) {
		this.aKw = aKw;
	}

	public String getbKw() {
		return bKw;
	}

	public void setbKw(String bKw) {
		this.bKw = bKw;
	}

	public String getcKw() {
		return cKw;
	}

	public void setcKw(String cKw) {
		this.cKw = cKw;
	}

	public String getKwhTotal() {
		return kwhTotal;
	}

	public void setKwhTotal(String kwhTotal) {
		this.kwhTotal = kwhTotal;
	}

	public String getaKwh() {
		return aKwh;
	}

	public void setaKwh(String aKwh) {
		this.aKwh = aKwh;
	}

	public String getbKwh() {
		return bKwh;
	}

	public void setbKwh(String bKwh) {
		this.bKwh = bKwh;
	}

	public String getcKwh() {
		return cKwh;
	}

	public void setcKwh(String cKwh) {
		this.cKwh = cKwh;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getCircuitName() {
		return circuitName;
	}

	public void setCircuitName(String circuitName) {
		this.circuitName = circuitName;
	}
	
	
	
}
