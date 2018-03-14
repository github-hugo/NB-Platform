package com.nhb.nb.request.device;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceRequest {
	
	@XmlElement(name="deviceId")
	@ApiModelProperty(value="deviceId",required=true,example="UUID-87888889sd21")
	private String deviceId;
	
	@XmlElement(name = "nodeId")
	@ApiModelProperty(value = "nodeId", required = true, example = "TESTS_201803")
	private String nodeId;
	
	@XmlElement(name="name")
	@ApiModelProperty(value="name",required=true,example="NBDevice")
	private String name;
	
	@XmlElement(name="verifyCode")
	@ApiModelProperty(value="verifyCode",required=true,example="TESTS_201803")
	private String verifyCode;
	
	@XmlElement(name="description")
	@ApiModelProperty(value="description",required=true,example="NB-Device")
	private String description;

	@XmlElement(name="manufacturerId")
	@ApiModelProperty(value="manufacturerId",required=true,example="Newhongbo")
	private String manufacturerId;

	@XmlElement(name="manufacturerName")
	@ApiModelProperty(value="manufacturerName",required=true,example="Newhongbo")
	private String manufacturerName;
	
	@XmlElement(name="location")
	@ApiModelProperty(value="location",required=true,example="胜港街88号")
	private String location;

	@XmlElement(name="deviceType")
	@ApiModelProperty(value="deviceType",required=true,example="ThreePhaseFourCircuits")
	private String deviceType;
	
	@XmlElement(name="protocolType")
	@ApiModelProperty(value="protocolType",required=true,example="CoAP")
	private String protocolType;

	@XmlElement(name="model")
	@ApiModelProperty(value="model",required=true,example="NBIoTDevice")
	private String model;
	
	@XmlElement(name = "endUserId")
	@ApiModelProperty(value = "endUserId", required = true, example = "区域Id")
	private String endUserId;
	
	@XmlElement(name = "region")
	@ApiModelProperty(value = "region", required = true, example = "苏州")
	private String region;

	@XmlElement(name = "organization")
	@ApiModelProperty(value = "organization", required = true, example = "Huawei")
	private String organization;
	
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

	
//	@XmlElement(name = "pageNo")
//	@ApiModelProperty(value = "pageNo", required = true, example = "1")
//	private Integer pageNo;
//
//	@XmlElement(name = "pageSize")
//	@ApiModelProperty(value = "pageSize", required = true, example = "10")
//	private Integer pageSize;

	public String getDeviceId() {
		return deviceId;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getEndUserId() {
		return endUserId;
	}

	public void setEndUserId(String endUserId) {
		this.endUserId = endUserId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
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
