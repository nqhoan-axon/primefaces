package com.sample.primefaces.autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class CountryService {

	private static CountryService instance;
	private List<Country> countries;
	private Map<Integer, Country> countriesAsMap;

	public static CountryService getInstance() {
		if (instance == null) {
			instance = new CountryService();
		}
		return instance;
	}

	private void initData() {
		countries = new ArrayList<>();
		String[] isoCodes = Locale.getISOCountries();

		for (int i = 0; i < isoCodes.length; i++) {
			Locale locale = new Locale("", isoCodes[i]);
			countries.add(new Country(i, locale));
		}

		Collections.sort(countries, (Country c1, Country c2) -> c1.getName().compareTo(c2.getName()));
	}

	public List<Country> getCountries() {
		if (countries == null || countries.isEmpty()) {
			initData();
		}
		return new ArrayList<>(countries);
	}

	public Map<Integer, Country> getCountriesAsMap() {
		if (countries == null || countries.isEmpty()) {
			initData();
		}
		if (countriesAsMap == null) {
			countriesAsMap = getCountries().stream().collect(Collectors.toMap(Country::getId, country -> country));
		}
		return countriesAsMap;
	}
}
