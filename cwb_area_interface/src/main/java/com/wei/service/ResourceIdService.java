package com.wei.service;

import java.util.List;

public interface ResourceIdService {
	public String getResourceId(String area, int period);
	public List<String> getTowns(String area);
	public void close();
}
