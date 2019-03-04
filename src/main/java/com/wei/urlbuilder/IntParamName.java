package com.wei.urlbuilder;

public enum IntParamName {
	LIMIT("limit"), OFFSET("offset");
	
	private String fieldName;
	
	private IntParamName(String name) {
		this.fieldName = name;
	}
	
	public String fieldName() {
		return fieldName;
	}
}
