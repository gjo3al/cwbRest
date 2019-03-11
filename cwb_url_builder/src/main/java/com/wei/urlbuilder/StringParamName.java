package com.wei.urlbuilder;

public enum StringParamName {
	FORMAT("format"), SORT("sort"), TIMEFROM("timeFrom"), TIMETO("timeTo");
	
	private String fieldName;
	
	private StringParamName(String name) {
		this.fieldName = name;
	}
	
	public String fieldName() {
		return fieldName;
	}
}
