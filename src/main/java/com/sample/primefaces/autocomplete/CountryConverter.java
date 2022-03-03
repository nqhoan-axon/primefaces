package com.sample.primefaces.autocomplete;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("countryConverter")
public class CountryConverter implements Converter {

	@Override
	public Country getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return CountryService.getInstance().getCountriesAsMap().get(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
			}
		} else {
			return null;
		}
	}

	public String getCountryAsString(Country value) {
		if (value != null) {
			return String.valueOf(value.getId());
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		if (value != null && value instanceof Country) {
			return getCountryAsString((Country) value);
		} else {
			return null;
		}
	}
}
