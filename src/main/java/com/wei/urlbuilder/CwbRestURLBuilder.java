package com.wei.urlbuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CwbRestURLBuilder {
	
	private Properties properties;
	
	private StringBuilder URLString;
	private Map<String, Integer> intParams = new HashMap<>();
	private Map<String, String> stringParams = new HashMap<>();
	private Map<String, List<String>> listParams = new HashMap<>();
	
	/*private int limit;
	private int offset;
	private String format;
	private List<String> locationName = new ArrayList<>();
	private List<String> elementName = new ArrayList<>();
	private String sort;
	private List<String> startTime;
	private String timeFrom;
	private String timeTo;*/
	
	public CwbRestURLBuilder(String resourceId) {
		properties = new Properties();
		String configFile = "src/main/resources/data.properties";
		
		try {
			properties.load(new FileInputStream(configFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.URLString = new StringBuilder(properties.getProperty("CWB_OPENDATA_REST_URL"));
		URLString.append("/")
				.append(resourceId)
				.append("?Authorization=")
				.append(properties.getProperty("Authorization"));
	}
		
	public CwbRestURLBuilder setIntParam(IntParamName name, int value) {
		intParams.put(name.fieldName(), value);
		return this;
	}
	
	public CwbRestURLBuilder setStringParam(StringParamName name, String value) {
		stringParams.put(name.fieldName(), value);
		return this;
	}
	
	public CwbRestURLBuilder addListParam(ListParamName name, String... values) {
		String fieldName = name.fieldName();
		if(!listParams.containsKey(fieldName)) {
			listParams.put(fieldName, new ArrayList<String>());
		}
		listParams.get(fieldName).addAll(Arrays.asList(values));
		return this;
	}
	
	public URL build() throws MalformedURLException {
		addParams();
		return new URL(URLString.toString());
	}
	
	private CwbRestURLBuilder addParams() {
		intParams.forEach((key, value) -> {
			URLString.append("&")
					.append(key)
					.append("=")
					.append(value);
		});
		
		stringParams.forEach((key, value) -> {
			URLString.append("&")
					.append(key)
					.append("=")
					.append(value);
		});
		
		listParams.forEach((key, values) -> {
			URLString.append("&")
					.append(key)
					.append("=")
					.append(listParamsToString(values));
		});
		return this;
	}
	
	private String listParamsToString(List<String> values) {
		StringBuilder stringOfValues = new StringBuilder();
		values.forEach(value -> {
			stringOfValues.append(value).append(",");
		});
		stringOfValues.deleteCharAt(stringOfValues.length() - 1);
		return stringOfValues.toString();
	}
	
	@Override
	public String toString() {
		return URLString.toString();
	}
}
