package com.market.survey.core.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.market.survey.core.domain.Party;

public interface PartyService {
	 List<Party> getAllParties();
	 Page<Party> getAllPartiesByPage(Pageable page);
	 Party getPartyById(Long articleId);
	 Party addParty(Party party);
     void updateParty(Party party);
     void deleteParty(long id);
     Party findByName(String name);
}
