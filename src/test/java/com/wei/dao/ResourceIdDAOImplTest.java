package com.wei.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ResourceIdDAOImplTest {

	private static ResourceIdDAO resourceIdDAO;
	
	@BeforeClass
	public static void getResourceIdDAO() {
		resourceIdDAO = new ResourceIdDAOImpl();
	}
	
	@Test
	public void testGetResourceId_exist() {
		assertThat(resourceIdDAO.getResourceId("新竹市", 7), is("F-D0047-055"));
	}
	
	@Test
	public void testGetResourceId_notExist() {
		assertThat(resourceIdDAO.getResourceId("新竹市", 5), is(""));
	}
	
	@Test
	public void testGetTowns_exist() {
		assertThat(resourceIdDAO.getTowns("新竹市"), hasSize(3));
	}
	
	@Test
	public void testGetTowns_notExist() {
		assertThat(resourceIdDAO.getTowns("竹市"), is(empty()));
	}
	
	@AfterClass
	public static void afterTest() {
		resourceIdDAO.close();
	}

}
