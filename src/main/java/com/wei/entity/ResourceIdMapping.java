package com.wei.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="area_resource_id")
public class ResourceIdMapping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="resource_id")
	private String resourceId;
	
	@Column(name="area")
	private String area;
	
	@Column(name="period")
	private int period;

	public ResourceIdMapping() {
		// TODO Auto-generated constructor stub
	}

	public ResourceIdMapping(String resourceId, String area, int period) {
		this.resourceId = resourceId;
		this.area = area;
		this.period = period;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "ResourceIdMapping [resourceId=" + resourceId + ", area=" + area + ", period=" + period + "]";
	}
	
}
