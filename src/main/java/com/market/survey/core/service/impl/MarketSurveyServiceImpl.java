package com.market.survey.core.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.market.survey.core.domain.MarketSurvey;
import com.market.survey.core.domain.Party;
import com.market.survey.core.repository.MarketSurveyRepository;
import com.market.survey.core.service.MarketSurveyService;

@Service
public class MarketSurveyServiceImpl implements MarketSurveyService {
	@Autowired
	MarketSurveyRepository repository;

	@Override
	public List<MarketSurvey> getAllMarketSurveys() {
		return repository.findAll();
	}

	@Override
	public Page<MarketSurvey> getAllMarketSurveysByPage(Pageable page) {
		return repository.findAll(page);
	}

	@Override
	public MarketSurvey getMarkeySurveyById(Long id) throws NoSuchElementException {
		return repository.findById(id).get();
	}

	@Override
	public MarketSurvey addMarketSurvey(MarketSurvey marketSurvey) {
		return repository.save(marketSurvey);
	}

	@Override
	public void updateMarketSurvey(MarketSurvey marketSurvey) {
		repository.save(marketSurvey);

	}

	@Override
	public void deleteMarketSurvey(long id) {
		repository.deleteById(id);

	}

	@Override
	public List<MarketSurvey> findByRequesters(Party requester) {

		return repository.findByRequesters(requester);

	}

}
