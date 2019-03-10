package com.wei.dao;

import java.util.List;

public interface ResourceIdDAO {
	public String getResourceId(String area, int period);
	public List<String> getTowns(String area);
	public void close();
}
