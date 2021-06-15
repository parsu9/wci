package com.restcountries.countries.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.restcountries.countries.bo.CountriesBO;
@Component
public class SortJsonDataByArea {
	public static List<CountriesBO> sortingJsonData(List<CountriesBO> list) {
		Collections.sort(list, new Comparator<CountriesBO>() {
			@Override
			public int compare(CountriesBO o1, CountriesBO o2) {
				return -Double.valueOf(o1.getArea()).compareTo(Double.valueOf(o2.getArea()));
			}
		});
		List<CountriesBO> sortedlist = new ArrayList<CountriesBO>();
		for (int i = 0; i < 10; i++) {
			if (list.get(i).getArea() != 0 || list.get(i).getArea() != Double.NaN) {
				sortedlist.add(i, list.get(i));
			}
		}
		return sortedlist;
	}
}
