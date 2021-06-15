package com.restcountries.countries.utility;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.restcountries.countries.bo.CountriesBO;

public class CreateCsv {

	public static void createCsv(String csvName, HttpServletResponse response, List<CountriesBO> list) {
		try {
			response.setContentType("text/csv");
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=" + csvName + ".csv";
			response.setHeader(headerKey, headerValue);

			ICsvBeanWriter writer = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
			String[] csvHeader = { "Name", "Capital", "Region", "Subregion", "Population", "Area", "Borders" };
			String[] nameMapping = { "name", "capital", "region", "subregion", "population", "area", "borders" };
			writer.writeHeader(csvHeader);
			for (CountriesBO cbo : list) {
				writer.write(cbo, nameMapping);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
