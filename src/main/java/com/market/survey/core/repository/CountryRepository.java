package com.market.survey.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.market.survey.core.domain.Country;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository< Country, Long>{

}
