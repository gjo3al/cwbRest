package com.wei.utility;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wei.urlbuilder.CwbRestURLBuilder;
import com.wei.urlbuilder.IntParamName;
import com.wei.urlbuilder.ListParamName;
import com.wei.urlbuilder.StringParamName;

public class URLBuilderTest {

	private static CwbRestURLBuilder builder;
	
	@BeforeClass
	public static void setUp() {
		builder = new CwbRestURLBuilder("新竹市", 7);
	}
	
	@Before
	public void resetBuilder() {
		builder.resetUrl();
	}
	
	@Test
	public void testURLBuilderBuild_correct() throws MalformedURLException {
		assertThat(builder.build().toString(), 
				is("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-055?Authorization=CWB-6C72897A-C429-49C1-84F7-7FE7D95071AC"));
	}
	
	@Test
	public void testSetIntParam() {
		builder.setIntParam(IntParamName.LIMIT, 1);
		String urlString = builder.getCurrentUrl();
		
		assertThat(urlString, 
				containsString("&limit=1"));
	}
	
	@Test
	public void testSetStringParam() {
		builder.setStringParam(StringParamName.SORT, "time");
		String urlString = builder.getCurrentUrl();
		
		assertThat(urlString, 
				containsString("&sort=time"));
	}
	
	@Test
	public void testAddListParam_miltiElements() {
		builder.addListParam(ListParamName.ELEMENTNAME, "PoP", "CI");
		String urlString = builder.getCurrentUrl();
		
		assertThat(urlString, 
				containsString("&elementName=PoP,CI"));
	}
	
	@Test
	public void testResetUrl() {
		builder.addListParam(ListParamName.ELEMENTNAME, "PoP", "CI");
		builder.resetUrl();
		String urlString = builder.getCurrentUrl();
		
		assertThat(urlString, 
				is("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-055?Authorization=CWB-6C72897A-C429-49C1-84F7-7FE7D95071AC"));
	}
	
	@AfterClass
	public static void close() {
		builder.close();
	}
}
