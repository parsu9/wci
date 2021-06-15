package com.restcountries.countries.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.restcountries.countries.bo.CountriesBO;
import com.restcountries.countries.utility.CountryListObjMap;
import com.restcountries.countries.utility.SortJsonDataByArea;
import com.restcountries.countries.utility.UrlConnection;

@Service
public class CountiresServiceImpl implements CountriesService {
	private static final String RestCountriesURL = "https://restcountries.eu/rest/v2";

	@Override
	@Cacheable(value = "allCountriesDetails")
	public List<CountriesBO> getAllCountiresData() {
		return CountryListObjMap.getMappedObj(UrlConnection.getConnection(RestCountriesURL.concat("/all")));
	}

	@Override
	@Cacheable(value = "tenBiggestRegions")
	public List<CountriesBO> getTenBiggestRegionDetails() {
		List<CountriesBO> list = null;
		list = CountryListObjMap.getMappedObj(UrlConnection.getConnection(RestCountriesURL.concat("/all")));
		return SortJsonDataByArea.sortingJsonData(list);
	}

	@Override
	@Cacheable(value = "TenBiggestRegionCountries", key = "#regionName")
	public List<CountriesBO> getTenBiggestRegionCountriesDetails(String regionName) {
		List<CountriesBO> list = null;
		list = CountryListObjMap
				.getMappedObj(UrlConnection.getConnection(RestCountriesURL.concat("/region/" + regionName)));
		return SortJsonDataByArea.sortingJsonData(list);
	}

	@Override
	@Cacheable(value = "TenSubRegionCountries", key = "#subRegionName")
	public List<CountriesBO> getSubRegionCountriesDetails(String subRegionName) {
		List<CountriesBO> list = null;
		List<CountriesBO> sortedList = new ArrayList<CountriesBO>();
		list = CountryListObjMap
				.getMappedObj(UrlConnection.getConnection(RestCountriesURL.concat("/subregion/" + subRegionName)));
		for (CountriesBO li : list) {
			if (li.getBorders().size() > 3) {
				sortedList.add(li);
			}
		}

		return sortedList;
	}

	@Override
	public String getPopulationSubRegionCountries(String subRegionName) {
		long totalPopulation = 0;
		List<CountriesBO> list = CountryListObjMap
				.getMappedObj(UrlConnection.getConnection(RestCountriesURL.concat("/subregion/" + subRegionName)));
		for (CountriesBO countriesBO : list) {
			totalPopulation = (totalPopulation + countriesBO.getPopulation());
		}
		return "Total Population of subregion \"" + subRegionName + "\" is " + totalPopulation;
	}
}
