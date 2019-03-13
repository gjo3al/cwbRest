package com.wei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wei.service.ResourceIdService;

@Controller
public class ResourceIdControllerImpl implements ResourceIdController {

	@Autowired
	private ResourceIdService resourceIdService;
	
	@Override
	public String getResourceId(String area, int period) {
		return resourceIdService.getResourceId(area, period);
	}

	@Override
	public List<String> getTowns(String area) {
		return resourceIdService.getTowns(area);
	}

	@Override
	public void close() {
		resourceIdService.close();
	}
	
	@GetMapping("/resourceId/{area}/{period}")
	@ResponseBody
	public String resourceId(@PathVariable String area, @PathVariable int period) {
		return getResourceId(area, period);
	}
	
	@GetMapping("/hello")
	@ResponseBody
	public String index() {
		return "hello";
	}
}
