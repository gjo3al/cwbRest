package com.wei.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.wei.config.TestConfig;

public class ResourceIdServiceImplTest {

	private static ResourceIdService resourceIdService;
	
	@BeforeClass
	public static void getResourceIdService() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class);
		resourceIdService = 
				ctx.getBean(ResourceIdService.class);
	}
	
	@Test
	public void testGetResourceId_exist() {
		assertThat(resourceIdService.getResourceId("新竹市", 7), is("F-D0047-055"));
	}
	
	@Test
	public void testGetResourceId_notExist() {
		assertThat(resourceIdService.getResourceId("新竹市", 5), is(""));
	}
	
	@Test
	public void testGetTowns_exist() {
		assertThat(resourceIdService.getTowns("新竹市"), hasSize(3));
	}
	
	@Test
	public void testGetTowns_notExist() {
		assertThat(resourceIdService.getTowns("竹市"), is(empty()));
	}
	
	@AfterClass
	public static void afterTest() {
		resourceIdService.close();
	}

}
