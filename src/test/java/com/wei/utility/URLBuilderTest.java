package com.wei.utility;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.wei.urlbuilder.CwbRestURLBuilder;
import com.wei.urlbuilder.IntParamName;
import com.wei.urlbuilder.ListParamName;
import com.wei.urlbuilder.StringParamName;

public class URLBuilderTest {

	private CwbRestURLBuilder builder;
	
	@Before
	public void setUp() {
		builder = new CwbRestURLBuilder("testId");
	}
	
	@Test
	public void testURLBuilderConstructor_correct() {
		assertThat(builder.toString(), 
				is("https://opendata.cwb.gov.tw/api/v1/rest/datastore/testId?Authorization=CWB-6C72897A-C429-49C1-84F7-7FE7D95071AC"));
	}
	
	@Test
	public void testSetIntParam() throws MalformedURLException {
		builder.setIntParam(IntParamName.LIMIT, 1);
		String URLString = builder.build().toString();
		
		assertThat(URLString, 
				containsString("&limit=1"));
	}
	
	@Test
	public void testSetStringParam() throws MalformedURLException {
		builder.setStringParam(StringParamName.SORT, "time");
		String URLString = builder.build().toString();
		
		assertThat(URLString, 
				containsString("&sort=time"));
	}
	
	@Test
	public void testAddListParam_miltiElements() throws MalformedURLException {
		builder.addListParam(ListParamName.ELEMENTNAME, "PoP", "CI");
		String URLString = builder.build().toString();
		
		assertThat(URLString, 
				containsString("&elementName=PoP,CI"));
	}
}
