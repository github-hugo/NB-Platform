package com.nhb.nb.request.device;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel
public class RegisterDeviceRequest {

	@XmlElement(name = "verifyCode")
	@ApiModelProperty(value = "verifyCode", required = true, example = "TESTS_20180103")
	private String verifyCode;

	@XmlElement(name = "nodeId")
	@ApiModelProperty(value = "nodeId", required = true, example = "TESTS_20180103")
	private String nodeId;

	@XmlElement(name = "endUserId")
	@ApiModelProperty(value = "endUserId", required = true, example = "")
	private String endUserId;

	@XmlElement(name = "psk")
	@ApiModelProperty(value = "psk", required = true, example = "")
	private String psk;

	@XmlElement(name = "timeout")
	@ApiModelProperty(value = "timeout", required = true, example = "0")
	private Integer timeout;

	@XmlElement(name = "longitude")
	@ApiModelProperty(value = "longitude", required = true, example = "118.322")
	private Double longitude;

	@XmlElement(name = "latitude")
	@ApiModelProperty(value = "latitude", required = true, example = "76.4343")
	private Double latitude;

	@XmlElement(name = "gpsLng")
	@ApiModelProperty(value = "gpsLng", required = true, example = "118.2372362")
	private Double gpsLng;

	@XmlElement(name = "gpsLat")
	@ApiModelProperty(value = "gpsLat", required = true, example = "76.435453")
	private Double gpsLat;

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getEndUserId() {
		return endUserId;
	}

	public void setEndUserId(String endUserId) {
		this.endUserId = endUserId;
	}

	public String getPsk() {
		return psk;
	}

	public void setPsk(String psk) {
		this.psk = psk;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getGpsLng() {
		return gpsLng;
	}

	public void setGpsLng(Double gpsLng) {
		this.gpsLng = gpsLng;
	}

	public Double getGpsLat() {
		return gpsLat;
	}

	public void setGpsLat(Double gpsLat) {
		this.gpsLat = gpsLat;
	}

}
