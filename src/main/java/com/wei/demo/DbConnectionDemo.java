package com.wei.demo;

import com.wei.dao.ResourceIdDAO;
import com.wei.dao.ResourceIdDAOImpl;

public class DbConnectionDemo {

	public static void main(String[] args) {
		ResourceIdDAO resourceIdDAO = new ResourceIdDAOImpl();
		
		System.out.println(resourceIdDAO.getTowns("新竹市"));
	}

}
