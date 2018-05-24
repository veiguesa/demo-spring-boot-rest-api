package com.market.survey.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.market.survey.core.domain.MarketSurvey;
import com.market.survey.core.domain.Party;



public interface MarketSurveyService {
	 List<MarketSurvey> getAllMarketSurveys();
	 Page<MarketSurvey> getAllMarketSurveysByPage(Pageable page);
	 MarketSurvey getMarkeySurveyById(Long articleId);
	 MarketSurvey addMarketSurvey(MarketSurvey marketSurvey);
     void updateMarketSurvey(MarketSurvey marketSurvey);
     void deleteMarketSurvey(long id);
     List<MarketSurvey> findByRequesters(Party requesters);
     
}
