package com.nhb.nb.dataaccess.entity;

import java.util.Date;

public class Log {
	
	private String id;
	
	private String userId;
	
	private String module;
	
	private String method;
	
	private String responseTime;
	
	private String ip;
	
	private Date operateTime;
	
	private String commitStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getCommitStatus() {
		return commitStatus;
	}

	public void setCommitStatus(String commitStatus) {
		this.commitStatus = commitStatus;
	}
	
	
	
	
}
