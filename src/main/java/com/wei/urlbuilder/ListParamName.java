package com.wei.urlbuilder;

public enum ListParamName {
	LOCATIONNAME("locationName"), ELEMENTNAME("elementName"), STARTTIME("startTime");
	
	private String fieldName;
	
	private ListParamName(String name) {
		this.fieldName = name;
	}
	
	public String fieldName() {
		return fieldName;
	}
}
