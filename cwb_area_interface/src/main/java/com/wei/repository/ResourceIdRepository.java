package com.wei.repository;

import java.util.List;

public interface ResourceIdRepository {
	public String getResourceId(String area, int period);
	public List<String> getTowns(String area);
	public void close();
}
