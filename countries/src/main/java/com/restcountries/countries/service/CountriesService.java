package com.restcountries.countries.service;

import java.util.List;

import com.restcountries.countries.bo.CountriesBO;

public interface CountriesService {

	public List<CountriesBO> getAllCountiresData();

	public List<CountriesBO> getTenBiggestRegionDetails();

	public List<CountriesBO> getTenBiggestRegionCountriesDetails(String regionName);

	public List<CountriesBO> getSubRegionCountriesDetails(String subRegionName);

	public String getPopulationSubRegionCountries(String subRegionName);
	
}
