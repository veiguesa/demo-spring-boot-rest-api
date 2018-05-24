package com.market.survey.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.market.survey.core.domain.MarketSurvey;
import com.market.survey.core.domain.Party;

@Repository
@Transactional
public interface MarketSurveyRepository extends JpaRepository< MarketSurvey, Long>{
List<MarketSurvey> findByRequesters(Party requester);

}
