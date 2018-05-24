package com.market.survey.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.market.survey.core.domain.Country;
import com.market.survey.core.domain.MarketSurvey;
import com.market.survey.core.repository.CountryRepository;
import com.market.survey.core.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	CountryRepository repository;

	@Override
	public List<Country> getAllCountries() {
		return repository.findAll();
	}

	@Override
	public Page<Country> getAllCountriesByPage(Pageable page) {
		return repository.findAll(page);
	}

	@Override
	public Country getMarkeySurveyById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Country addCountry(Country country) {
		return repository.save(country);
	}

	@Override
	public void updateCountry(Country country) {
		repository.save(country);

	}

	@Override
	public void deleteCountry(long id) {
		repository.deleteById(id);

	}
}
