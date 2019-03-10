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

import com.wei.dao.ResourceIdDAO;
import com.wei.dao.ResourceIdDAOImpl;

public class CwbRestURLBuilder {
	
	private final String CONFIG_FILE = "src/main/resources/data.properties";
	
	private ResourceIdDAO resourceIdDAO = new ResourceIdDAOImpl();
	
	private StringBuilder urlString;
	private String urlWithoutParams;
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
	
	public CwbRestURLBuilder(String area, int period) {
		this.urlString = getRestUrl(area, period);
		this.urlWithoutParams = urlString.toString();
	}

	private StringBuilder getRestUrl(String area, int period) {
		
		Properties properties = new Properties();
		StringBuilder builder = new StringBuilder("");
		try {
			properties.load(new FileInputStream(CONFIG_FILE));
			String url = properties.getProperty("CWB_OPENDATA_REST_URL");
			String resourceId = resourceIdDAO.getResourceId(area, period);
			String authorization = properties.getProperty("Authorization");
			
			builder.append(url)
					.append("/").append(resourceId)
					.append("?Authorization=").append(authorization);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return builder;
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
	
	public String getCurrentUrl() {
		addParams();
		return urlString.toString();
	}

	public void resetUrl() {
		clearParams();
		urlString.setLength(0);
		urlString.append(urlWithoutParams);
	}
	
	public URL build() throws MalformedURLException {
		addParams();
		return new URL(urlString.toString());
	}
	
	public void close() {
		resourceIdDAO.close();
	}
	
	private CwbRestURLBuilder addParams() {
		intParams.forEach((key, value) -> {
			urlString.append("&")
					.append(key)
					.append("=")
					.append(value);
		});
		
		stringParams.forEach((key, value) -> {
			urlString.append("&")
					.append(key)
					.append("=")
					.append(value);
		});
		
		listParams.forEach((key, values) -> {
			urlString.append("&")
					.append(key)
					.append("=")
					.append(listParamsToString(values));
		});
		
		clearParams() ;
		
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
	
	private void clearParams() {
		intParams.clear();
		stringParams.clear();
		listParams.clear();
	}
	
	@Override
	public String toString() {
		return urlString.toString();
	}
	
}
