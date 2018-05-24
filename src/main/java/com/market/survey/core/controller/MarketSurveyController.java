package com.market.survey.core.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.market.survey.core.domain.MarketSurvey;
import com.market.survey.core.domain.Party;
import com.market.survey.core.service.MarketSurveyService;
import com.market.survey.core.service.PartyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import market.survey.core.utils.SecurityUtils;

@RestController
@RequestMapping("/api")
@Api(value = "onlinestore", description = "Operations with MarketSurvey")
public class MarketSurveyController {

	@Autowired
	MarketSurveyService marketService;

	@Autowired
	PartyService partyService;

	// long way --> @RequestMapping(value="/surveys", method=RequestMethod.GET)
	@ApiOperation(value = "View a list of available surveys.Admin can list all . The user can only list wherever resquester is required ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized") })
	@GetMapping("/surveys")
	public ResponseEntity<List<MarketSurvey>> getAllSurveys(Authentication authentication) {

		List<MarketSurvey> list = null;

		if (SecurityUtils.checkAdminRole(authentication)) {
			list = marketService.getAllMarketSurveys();
		} else {
			Party p = partyService.findByName(authentication.getName());
			list = marketService.findByRequesters(p);
		}

		return new ResponseEntity<List<MarketSurvey>>(list, HttpStatus.OK);

	}

	/**
	 * example : /api/paginated/surveys?page=0&size=2
	 * 
	 * @param page
	 * @return
	 */
	@ApiOperation(value = "View a list of available surveys by page.(only admin) . ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized ") })
	@GetMapping("/paginated/surveys")
	public ResponseEntity<Page<MarketSurvey>> getAllByPage(Pageable page, Authentication authentication) {

		if (!SecurityUtils.checkAdminRole(authentication))
			throw new AccessDeniedException(null);
		Page<MarketSurvey> list = marketService.getAllMarketSurveysByPage(page);

		return new ResponseEntity<Page<MarketSurvey>>(list, HttpStatus.OK);

	}

	@ApiOperation(value = "add a new survey.(only admin) . ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Add successfully "),
			@ApiResponse(code = 401, message = "You are not authorized ") })
	@PostMapping("survey")
	public ResponseEntity<Void> addSurvey(@RequestBody MarketSurvey survey, UriComponentsBuilder builder,
			Authentication authentication) {
		if (!SecurityUtils.checkAdminRole(authentication))
			throw new AccessDeniedException(null);
		MarketSurvey add = marketService.addMarketSurvey(survey);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/survey/{id}").buildAndExpand(add.getIdentification()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "update a existing survey.(only admin) . ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "update successfully "),
			@ApiResponse(code = 401, message = "You are not authorized") })

	@PutMapping("survey/{id}")
	public ResponseEntity<MarketSurvey> updateSurvey(@RequestBody MarketSurvey survey, @PathVariable long id,
			Authentication authentication) {
		if (!SecurityUtils.checkAdminRole(authentication))
			throw new AccessDeniedException(null);
		MarketSurvey update_market = marketService.getMarkeySurveyById(id);
		if (update_market == null) {
			new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			survey.setIdentification(id);
			marketService.updateMarketSurvey(survey);
		}

		return new ResponseEntity<MarketSurvey>(survey, HttpStatus.OK);

	}

	@ApiOperation(value = "add requester to survey.(only admin) . ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Add successfully "),
			@ApiResponse(code = 401, message = "You are not authorized") })
	@PutMapping("survey/{idsurvey}/{idparty}")
	public ResponseEntity<MarketSurvey> addRequesterToSurvey(@PathVariable long idsurvey, @PathVariable long idparty,
			Authentication authentication) {
		if (!SecurityUtils.checkAdminRole(authentication))
			throw new AccessDeniedException(null);
		MarketSurvey update_market = marketService.getMarkeySurveyById(idsurvey);
		Party p = partyService.getPartyById(idparty);
		if (update_market != null && p != null) {
			update_market.getRequesters().add(p);
			marketService.updateMarketSurvey(update_market);
		}

		return new ResponseEntity<MarketSurvey>(update_market, HttpStatus.OK);
	}

	@ApiOperation(value = "delete survey.(only admin) . ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "delete successfully "),
			@ApiResponse(code = 401, message = "You are not authorized") })

	@DeleteMapping("survey/{id}")
	public ResponseEntity<Void> deleteSurvey(@PathVariable long id, Authentication authentication) {
		if (!SecurityUtils.checkAdminRole(authentication))
			throw new AccessDeniedException(null);
		marketService.deleteMarketSurvey(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
