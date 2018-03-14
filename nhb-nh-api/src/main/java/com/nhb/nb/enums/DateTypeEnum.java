package com.nhb.nb.enums;

public enum DateTypeEnum {

	DAY("DAY", "日"), MONTH("MONTH", "月");

	String key;

	String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private DateTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

}
