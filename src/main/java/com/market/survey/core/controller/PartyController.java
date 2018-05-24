package com.market.survey.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.market.survey.core.domain.Party;
import com.market.survey.core.service.PartyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "onlinestore", description = "Operations with Party")
public class PartyController {
	@Autowired
	PartyService partyService;

	@ApiOperation(value = "View a list of available parties.  ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized") })
	@GetMapping("/parties")
	public ResponseEntity<List<Party>> getAllParties() {

		List<Party> list = partyService.getAllParties();
		return new ResponseEntity<List<Party>>(list, HttpStatus.OK);

	}

	@ApiOperation(value = "View a list of available countries by page (/api/paginated/surveys?page=0&size=2)  ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized") })
	@GetMapping("/paginated/parties")

	public ResponseEntity<Page<Party>> getAllByPage(Pageable page) {
		Page<Party> list = partyService.getAllPartiesByPage(page);
		return new ResponseEntity<Page<Party>>(list, HttpStatus.OK);

	}

	@ApiOperation(value = "add a new party  ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = " add successfully "),
			@ApiResponse(code = 401, message = "You are not authorized") })

	@PostMapping("party")
	public ResponseEntity<Void> addParty(@RequestBody Party party, UriComponentsBuilder builder) {
		Party add = partyService.addParty(party);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/party/{id}").buildAndExpand(add.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "update a new party  ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = " update successfully "),
			@ApiResponse(code = 401, message = "You are not authorized") })
	@PutMapping("party/{id}")

	public ResponseEntity<Party> updateParty(@RequestBody Party party, @PathVariable long id) {
		Party update_party = partyService.getPartyById(id);
		if (update_party != null)
			new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		else
			party.setId(id);
		partyService.updateParty(party);
		return new ResponseEntity<Party>(party, HttpStatus.OK);
	}

	@ApiOperation(value = "delete a new party  ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = " update successfully "),
			@ApiResponse(code = 401, message = "You are not authorized") })
	@DeleteMapping("party/{id}")
	public ResponseEntity<Void> deleteParty(@PathVariable("id") Integer id) {
		partyService.deleteParty(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
