package com.wei.controller;

import java.util.List;

public interface ResourceIdController {
	public String getResourceId(String area, int period);
	public List<String> getTowns(String area);
	public void close();
}
