package com.restcountries.countries;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceTest {
	
	@Autowired
	private MockMvc mockmvc;

	@DisplayName("List all countries in the world")
	@Test
	public void testCountryList() throws Exception {
		this.mockmvc.perform(MockMvcRequestBuilders.get("/all")).andDo(print()).andExpect(status().isOk()).andReturn();
	}
	
	@DisplayName("List all countries in the world - csv Format")
	@Test
	public void testCountryListCsv() throws Exception {
		this.mockmvc.perform(MockMvcRequestBuilders.get("/all?responseType=csv")).andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@DisplayName("List of ten biggest region in the world")
	@Test
	public void testTenBiggestRegion() throws Exception {
		this.mockmvc.perform(MockMvcRequestBuilders.get("/tenbiggest/region")).andDo(print()).andExpect(status().isOk())
				.andReturn();
	}
	
	@DisplayName("List of ten biggest region in the world - CSV Format")
	@Test
	public void testTenBiggestRegionListCsv() throws Exception {
		this.mockmvc.perform(MockMvcRequestBuilders.get("/tenbiggest/region?responseType=csv")).andDo(print()).andExpect(status().isOk())
		.andReturn();
	}

	@DisplayName("List the 10 biggest countries of a determined region of the world")
	@ParameterizedTest
	@ValueSource(strings = { "Asia", "Europe", "Americas" })
	public void testTenBiggestRegionCountryList(String regionName) throws Exception {
		this.mockmvc.perform(MockMvcRequestBuilders.get("/tenbiggest/regionCountries/" + regionName)).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}
	
	@DisplayName("List the 10 biggest countries of determined region in the world - Csv format")
	@ParameterizedTest
	@ValueSource(strings = { "Asia", "Europe", "Americas" })
	public void testTenBiggestRegionCountryListCsv(String regionName) throws Exception {
		this.mockmvc.perform(MockMvcRequestBuilders.get("/tenbiggest/regionCountries/" + regionName +"?responseType=csv")).andDo(print())
		.andExpect(status().isOk()).andReturn();
	}

	private static List<String> subRegionLists() {
		return Arrays.asList("Southern Asia", "Eastern Europe", "Northern Europe", "South America");
	}

	@DisplayName("List of all countries of the determined subRegion")
	@ParameterizedTest()
	@MethodSource("subRegionLists")
	public void testSubregionCountryList(String subRegion) throws Exception {
		this.mockmvc.perform(MockMvcRequestBuilders.get("/subRegionCountries/" + subRegion)).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}
	
	@DisplayName("List of all countries of the determined subRegion - Csv Format")
	@ParameterizedTest()
	@MethodSource("subRegionLists")
	public void testSubregionCountryListCsv(String subRegion) throws Exception {
		this.mockmvc.perform(MockMvcRequestBuilders.get("/subRegionCountries/" + subRegion +"?responseType=csv")).andDo(print())
		.andExpect(status().isOk()).andReturn();
	}
}