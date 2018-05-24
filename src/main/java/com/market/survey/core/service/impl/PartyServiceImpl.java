package com.market.survey.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.market.survey.core.domain.Party;
import com.market.survey.core.repository.PartyRepository;
import com.market.survey.core.service.PartyService;

@Service
public class PartyServiceImpl implements PartyService {
	@Autowired
	PartyRepository repository;

	@Override
	public List<Party> getAllParties() {
		return repository.findAll();
	}

	@Override
	public Page<Party> getAllPartiesByPage(Pageable page) {
		return repository.findAll(page);
	}

	@Override
	public Party getPartyById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Party addParty(Party party) {
		return repository.save(party);
	}

	@Override
	public void updateParty(Party party) {
		repository.save(party);
		
	}

	@Override
	public void deleteParty(long id) {
		repository.deleteById(id);
		
	}
	@Override
	public Party findByName(String name){
		return repository.findByName(name);
	}
	

}
