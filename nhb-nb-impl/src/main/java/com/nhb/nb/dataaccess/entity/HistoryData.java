package com.nhb.nb.dataaccess.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import cn.afterturn.easypoi.excel.annotation.Excel;

@Document(collection = "history_data")
public class HistoryData {

	@Id
	@Excel(name = "标识")
	private String id;

	@Indexed
	@Field("device_id")
	@Excel(name = "设备Id",orderNum = "0")
	private String deviceId;

	@Indexed
	@Field("read_time")
	@Excel(name = "时间")//, exportFormat = "yyyy-MM-dd HH:mm:ss"
	private String readTime;
	
	@Indexed
	@Field("meter_time")
	@Excel(name = "时间")//, exportFormat = "yyyy-MM-dd HH:mm:ss"
	private String meterTime;

	@Field("service_id")
	@Excel(name = "服务Id")
	private String serviceId;

	@Field("data")
	@Excel(name = "数据")
	private Object data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getReadTime() {
		return readTime;
	}

	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}
	

	public String getMeterTime() {
		return meterTime;
	}

	public void setMeterTime(String meterTime) {
		this.meterTime = meterTime;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
