package com.wei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wei.repository.ResourceIdRepository;
import com.wei.service.ResourceIdService;

@Service
public class ResourceIdServiceImpl implements ResourceIdService {

	@Autowired
	private ResourceIdRepository resourceIdRepositrory;

	@Override
	public String getResourceId(String area, int period) {
		return resourceIdRepositrory.getResourceId(area, period);
	}

	@Override
	public List<String> getTowns(String area) {
		return resourceIdRepositrory.getTowns(area);
	}

	@Override
	public void close() {
		resourceIdRepositrory.close();
	}

}
