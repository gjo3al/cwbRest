package com.wei.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.wei.model.ResourceIdMapping;

public class DbConnectionDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(ResourceIdMapping.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<ResourceIdMapping> mappings = 
					session.createQuery(
							"from ResourceIdMapping data where data.area='新竹市'").getResultList();
			
			mappings.forEach(System.out::println);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close();
		}
		
	}

}
