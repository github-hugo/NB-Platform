package com.nhb.nb.dataaccess.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "device_info")
public class DeviceInfo {

	@Id
	@Field("device_id")
	private String deviceId;

	@Field("verify_code")
	private String verifyCode;

	@Field("psk")
	private String psk;

	@Field("timeout")
	private Integer timeout;

	// 这个是区域Id
	@Field("end_user_id")
	private String endUserId;

	@Field("node_id")
	private String nodeId;

	@Field("name")
	private String name;

	@Field("longitude")
	private Double longitude;

	@Field("latitude")
	private Double latitude;

	@Field("gps_lng")
	private Double gpsLng;

	@Field("gps_lat")
	private Double gpsLat;

	@Field("description")
	private String description;

	@Field("manufacture_id")
	private String manufactureId;

	@Field("manufacturer_name")
	private String manufacturerName;
	
	@Field("organization")
	private String organization;

	@Field("mac")
	private String mac;

	@Field("location")
	private String location;

	@Field("device_type")
	private String deviceType;

	@Field("model")
	private String model;

	@Field("sw_version")
	private String swVersion;

	@Field("fw_version")
	private String fwVersion;

	@Field("hw_version")
	private String hwVersion;

	@Field("protocol_type")
	private String protocolType;

	@Field("signal_strength")
	private String signalStrength;

	@Field("bridge_id")
	private String bridgeId;

	@Field("supported_security")
	private String supportedSecurity;

	@Field("is_security")
	private String isSecurity;

	@Field("sig_version")
	private String sigVersion;

	@Field("running_status")
	private String runningStatus;

	@Field("status")
	private String status;

	@Field("status_detail")
	private String statusDetail;

	@Field("mute")
	private String mute;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	public String getMute() {
		return mute;
	}

	public void setMute(String mute) {
		this.mute = mute;
	}

	public String getEndUserId() {
		return endUserId;
	}

	public void setEndUserId(String endUserId) {
		this.endUserId = endUserId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufactureId() {
		return manufactureId;
	}

	public void setManufactureId(String manufactureId) {
		this.manufactureId = manufactureId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSwVersion() {
		return swVersion;
	}

	public void setSwVersion(String swVersion) {
		this.swVersion = swVersion;
	}

	public String getFwVersion() {
		return fwVersion;
	}

	public void setFwVersion(String fwVersion) {
		this.fwVersion = fwVersion;
	}

	public String getHwVersion() {
		return hwVersion;
	}

	public void setHwVersion(String hwVersion) {
		this.hwVersion = hwVersion;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public String getSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(String signalStrength) {
		this.signalStrength = signalStrength;
	}

	public String getBridgeId() {
		return bridgeId;
	}

	public void setBridgeId(String bridgeId) {
		this.bridgeId = bridgeId;
	}

	public String getSupportedSecurity() {
		return supportedSecurity;
	}

	public void setSupportedSecurity(String supportedSecurity) {
		this.supportedSecurity = supportedSecurity;
	}

	public String getIsSecurity() {
		return isSecurity;
	}

	public void setIsSecurity(String isSecurity) {
		this.isSecurity = isSecurity;
	}

	public String getSigVersion() {
		return sigVersion;
	}

	public void setSigVersion(String sigVersion) {
		this.sigVersion = sigVersion;
	}

	public String getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(String runningStatus) {
		this.runningStatus = runningStatus;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	

}
