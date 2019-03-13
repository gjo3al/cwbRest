package com.wei.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.wei.entity.Country;
import com.wei.entity.CountryTownMapping;
import com.wei.entity.ResourceIdMapping;
import com.wei.repository.ResourceIdRepository;

@Repository
public class ResourceIdRepositoryImpl implements ResourceIdRepository {
	
	private SessionFactory factory;
	
	public ResourceIdRepositoryImpl() {
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(ResourceIdMapping.class)
				.addAnnotatedClass(Country.class)
				.addAnnotatedClass(CountryTownMapping.class)
				.buildSessionFactory();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getResourceId(String area, int period) {

		Session session = factory.getCurrentSession();
		String resourceId = "";
		try {
			session.beginTransaction();
			Query theQuery = 
				session.createQuery("from ResourceIdMapping data where data.area=:area and period=:period");
			theQuery.setParameter("area", area);
			theQuery.setParameter("period", period);
			List<ResourceIdMapping> results = theQuery.getResultList();
			if(!results.isEmpty()) 
				resourceId = results.get(0).getResourceId(); 
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resourceId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTowns(String area) {
		Session session = factory.getCurrentSession();
		List<String> towns = new ArrayList<>();
		
		try {
			session.beginTransaction();
			Query theQuery = 
					session.createQuery("from Country c where c.country=:country");
			theQuery.setParameter("country", area);
			
			List<Country> results = theQuery.getResultList();
			if(!results.isEmpty())
				towns = transferTownsToString(results.get(0).getTowns());
			
			// if we use 'Lazy' fetch, be sure 'use'(ex println) the lazy object before session commit
			// so weird!
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
		return towns;
	}

	@Override
	public void close() {
		factory.close();
	}

	private List<String> transferTownsToString(List<CountryTownMapping> townObjects) {
		return townObjects.stream().map(CountryTownMapping::getTown)
				.collect(Collectors.toList());
	}
	
}
