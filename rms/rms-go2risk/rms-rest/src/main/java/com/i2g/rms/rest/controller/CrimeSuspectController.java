package com.i2g.rms.rest.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.CrimeSuspectRO;
import com.i2g.rms.rest.model.lookup.CrimeSuspectTableRO;
import com.i2g.rms.rest.model.wrapper.CrimeSuspectWrapper;
import com.i2g.rms.rest.model.wrapper.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.CrimeSuspectRestService;

/**
 * Rest controller for suspect read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class CrimeSuspectController extends AbstractRestController {
	
	@Autowired
	private CrimeSuspectRestService _crimeSuspectRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_CRIME_SUSPECTS, method = RequestMethod.GET)
	@Searchable(sourceType = CrimeSuspectRO.class, value = CrimeSuspect.class)
	public List<CrimeSuspectRO> get() {
		return _crimeSuspectRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_CRIME_SUSPECT_TABLE_BY_CRIME_ID, method = RequestMethod.GET)
	public List<CrimeSuspectTableRO> getCrimeSuspectTableByCrimeId(@PathVariable final Long crimeId) {
		return _crimeSuspectRestService.getCrimeSuspectTableByCrimeId(crimeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_CRIME_SUSPECT_BY_CRIME_SUSPECT_ID, method = RequestMethod.GET)
	public CrimeSuspectRO get(@PathVariable final Long crimeSuspectId) {
		return _crimeSuspectRestService.get(crimeSuspectId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_CRIME_SUSPECT, method = RequestMethod.POST)
	public CrimeSuspectRO createCrimeSuspect(@Valid @RequestBody final CrimeSuspectRO crimeSuspectRO) {
		return _crimeSuspectRestService.createCrimeSuspect(crimeSuspectRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_CRIME_SUSPECTS, method = RequestMethod.POST)
	public Set<CrimeSuspectRO> createCrimeSuspects(@Valid @RequestBody final CrimeSuspectWrapper crimeSuspectWrapper) {
		return _crimeSuspectRestService.createCrimeSuspects(crimeSuspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_CRIME_SUSPECT, method = RequestMethod.PUT)
	public CrimeSuspectRO updateCrimeSuspect(@Valid @RequestBody final CrimeSuspectRO crimeSuspectRO) {
		return _crimeSuspectRestService.updateCrimeSuspect(crimeSuspectRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_CRIME_SUSPECTS, method = RequestMethod.PUT)
	public Set<CrimeSuspectRO> updateCrimeSuspects(@Valid @RequestBody final CrimeSuspectWrapper crimeSuspectWrapper) {
		return _crimeSuspectRestService.updateCrimeSuspects(crimeSuspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_DISTINGUISHING_FEATURE_DETAILS_FROM_CRIME_SUSPECT, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeDistinguishingFeatureDetailsFromCrimeSuspect(@Valid @RequestBody final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper) {
		_crimeSuspectRestService.removeDistinguishingFeatureDetailsFromCrimeSuspect(distinguishingFeatureDetailWrapper);
	}	
}
