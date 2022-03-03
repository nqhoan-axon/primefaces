package com.sample.primefaces.autocomplete;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean
@RequestScoped
public class AutoCompleteView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6552369435190102114L;
	private Country country1;
	private Country country2;
	private Country country3;
	
	public List<Country> completeCountry(String query) {
		String queryLowerCase = query.toLowerCase();
		List<Country> countries = CountryService.getInstance().getCountries();
		return countries.stream().filter(t -> t.getName().toLowerCase().contains(queryLowerCase))
				.collect(Collectors.toList());
	}

	public void onItemSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Country Selected" + event));
	}

	public void onEmptyMessageSelect() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empty message selected"));
	}

	public Country getCountry1() {
		return country1;
	}

	public void setCountry1(Country country1) {
		this.country1 = country1;
	}

	public Country getCountry2() {
		return country2;
	}

	public void setCountry2(Country country2) {
		this.country2 = country2;
	}

	public Country getCountry3() {
		return country3;
	}

	public void setCountry3(Country country3) {
		this.country3 = country3;
	}
}
