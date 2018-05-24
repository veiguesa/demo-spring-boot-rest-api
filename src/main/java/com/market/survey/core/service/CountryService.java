package com.market.survey.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.market.survey.core.domain.Country;

public interface CountryService {
	List<Country> getAllCountries();

	Page<Country> getAllCountriesByPage(Pageable page);

	Country getMarkeySurveyById(Long articleId);

	Country addCountry(Country country);

	void updateCountry(Country country);

	void deleteCountry(long id);
}
