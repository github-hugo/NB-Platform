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
public class UserRequest {

	@XmlElement(name = "userId")
	@ApiModelProperty(value = "userId", required = true, example = "1008")
	private String userId;

	@XmlElement(name = "userName")
	@ApiModelProperty(value = "userName", required = true, example = "admin")
	private String userName;

	@XmlElement(name = "password")
	@ApiModelProperty(value = "password", required = true, example = "123456")
	private String password;

	@XmlElement(name = "name")
	@ApiModelProperty(value = "name", required = true, example = "xyh")
	private String name;

	@XmlElement(name = "address")
	@ApiModelProperty(value = "address", required = true, example = "胜港街88号")
	private String address;

	@XmlElement(name = "email")
	@ApiModelProperty(value = "email", required = true, example = "xhb@newhongbo.com")
	private String email;

	@XmlElement(name = "phone")
	@ApiModelProperty(value = "phone", required = true, example = "15209786540")
	private String phone;

//	@XmlElement(name = "role")
//	@ApiModelProperty(value = "role", required = true, example = "admin")
//	private String role;
	
	@XmlElement(name = "pageNo")
	@ApiModelProperty(value = "pageNo", required = true, example = "1")
	private Integer pageNo;

	@XmlElement(name = "pageSize")
	@ApiModelProperty(value = "pageSize", required = true, example = "10")
	private Integer pageSize;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
