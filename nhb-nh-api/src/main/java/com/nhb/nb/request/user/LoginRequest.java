package com.nhb.nb.request.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginRequest {

	@XmlElement(name = "userId")
	@ApiModelProperty(value = "userId", required = true, example = "1008")
	private String userId;

	@XmlElement(name = "userName")
	@ApiModelProperty(value = "userName", required = true, example = "admin")
	private String userName;

	@XmlElement(name = "password")
	@ApiModelProperty(value = "password", required = true, example = "123456")
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
