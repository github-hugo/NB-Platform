package com.nhb.nb.request.device;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nhb.nb.dto.UpdateDeviceInfoReqDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel
public class UpdateDeviceInfoRequest {
	
	@XmlElement(name = "deviceId")
	@ApiModelProperty(value = "deviceId", required = true, example = "813a76b0-abb6-4131-bedc-49297377fc1b")
	private String deviceId;

	@XmlElement(name = "appId")
	@ApiModelProperty(value = "appId", required = true, example = "8hPQ3lYoNLBDMf_VydJPaxWbkoAa")
	private String appId;

	@XmlElement(name = "request")
	@ApiModelProperty(value = "request", required = true)
	private UpdateDeviceInfoReqDTO request;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public UpdateDeviceInfoReqDTO getRequest() {
		return request;
	}

	public void setRequest(UpdateDeviceInfoReqDTO request) {
		this.request = request;
	}

	
}
