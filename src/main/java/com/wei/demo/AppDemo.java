package com.wei.demo;

import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wei.urlbuilder.CwbRestURLBuilder;
import com.wei.urlbuilder.ListParamName;
import com.wei.urlbuilder.StringParamName;


public class AppDemo {

	public static void main(String[] args) {
		/*ObjectMapper mapper = new ObjectMapper();
		try {
			CwbRestURLBuilder builder = new CwbRestURLBuilder("F-D0047-003");
			URL url = builder.setStringParam(StringParamName.SORT, "time")
							.addListParam(ListParamName.LOCATIONNAME, "頭城鎮")
							.addListParam(ListParamName.ELEMENTNAME, "MinT", "WeatherDescription")
							.setStringParam(StringParamName.TIMEFROM, "2019-03-01T12:00:00")
							.setStringParam(StringParamName.TIMETO, "2019-03-02T22:00:00")
							.build();
			
			JsonNode weatherElementNode = 
					mapper.readTree(url).findPath("weatherElement");
			weatherElementNode.forEach(weatherElement -> {
				System.out.println(weatherElement.findValue("startTime"));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		String countries = "宜蘭縣,花蓮縣,臺東縣,澎湖縣,金門縣,連江縣,臺北市,新北市,\r\n" + 
				"桃園市,臺中市,臺南市,高雄市,基隆市,新竹縣,新竹市,苗栗縣,\r\n" + 
				"彰化縣,南投縣,雲林縣,嘉義縣,嘉義市,屏東縣";
		countries = countries.replaceAll("\\r\\n", "");
		for(String country:countries.split(",")) {
			System.out.printf("\t(\'%s\'),%n", country);	
		}
		
		
	}

}
