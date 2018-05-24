package com.market.survey.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.market.survey.core.domain.Country;
import com.market.survey.core.domain.Party;
import com.market.survey.core.service.CountryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Secured("ADMIN")
@Api(value = "onlinestore", description = "Operations with entity Country")
public class CountryController {
@Autowired 
CountryService countryService;

@ApiOperation(value = "View a list of available countries.  ", response = ResponseEntity.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized") })
@GetMapping("/countries")
public ResponseEntity<List<Country>> getAllCountries() {
	List<Country> list = countryService.getAllCountries();
	return new ResponseEntity<List<Country>>(list, HttpStatus.OK);

}
@ApiOperation(value = "View a list of available countries by page   ", response = ResponseEntity.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized") })
@GetMapping("/paginated/countries")
public ResponseEntity<Page<Country>> getAllByPage(Pageable page) {
	Page<Country> list =countryService.getAllCountriesByPage(page);
	return new ResponseEntity<Page<Country>>(list, HttpStatus.OK);

}
@ApiOperation(value = "Add a new country.  ", response = ResponseEntity.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully add"),
		@ApiResponse(code = 401, message = "You are not authorized") })
@PostMapping("countries")
public ResponseEntity<Void> addCountry(@RequestBody Country country, UriComponentsBuilder builder) {
	Country add = countryService.addCountry(country);
	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(builder.path("/country/{id}").buildAndExpand(add.getCode()).toUri());
	return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
}


@ApiOperation(value = "delete country  ", response = ResponseEntity.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
@ApiResponse(code = 401, message = "You are not authorized") })
@DeleteMapping("country/{id}")
public ResponseEntity<Void> deleteCountry(@PathVariable("id") Integer id) {
	countryService.deleteCountry(id);
	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
}
}
