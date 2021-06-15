package com.restcountries.countries.utility;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restcountries.countries.bo.CountriesBO;

/* Mapping JSON string with the POJO object */
public class CountryListObjMap {
	public static List<CountriesBO> getMappedObj(String ja) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<CountriesBO> list = null;
		try {
			list = mapper.readValue(ja, new TypeReference<List<CountriesBO>>() {
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return list;
	}
}
