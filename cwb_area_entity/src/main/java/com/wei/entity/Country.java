package com.wei.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="country")
	String country;

	@OneToMany(fetch=FetchType.LAZY, 
			cascade= {CascadeType.ALL})
	@JoinColumn(name="country")
	private List<CountryTownMapping> towns;
	
	public Country() {
	}
	
	public Country(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<CountryTownMapping> getTowns() {
		return towns;
	}

	public void setTowns(List<CountryTownMapping> towns) {
		this.towns = towns;
	}
	
	
}
