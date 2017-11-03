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

import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.InjuredPersonRO;
import com.i2g.rms.rest.model.lookup.InjuredPersonTableRO;
import com.i2g.rms.rest.model.wrapper.BodyPartWrapper;
import com.i2g.rms.rest.model.wrapper.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.model.wrapper.InjuredPersonWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.InjuredPersonRestService;

/**
 * Rest controller for InjuredPerson read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class InjuredPersonController extends AbstractRestController {
	
	@Autowired
	private InjuredPersonRestService _injuredPersonRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_INJURED_PERSONS, method = RequestMethod.GET)
	@Searchable(sourceType = InjuredPersonRO.class, value = InjuredPerson.class)
	public List<InjuredPersonRO> get() {
		return _injuredPersonRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_INJURED_PERSON_TABLE_BY_ACCIDENT_ID, method = RequestMethod.GET)
	public List<InjuredPersonTableRO> getInjuredPersonTableByAccidentId(@PathVariable final Long accidentId) {
		return _injuredPersonRestService.getInjuredPersonTableByAccidentId(accidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_INJURED_PERSON_BY_INJURED_PERSON_ID, method = RequestMethod.GET)
	public InjuredPersonRO get(@PathVariable final long injuredPersonId) {
		return _injuredPersonRestService.get(injuredPersonId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_INJURED_PERSON, method = RequestMethod.POST)
	public InjuredPersonRO createInjuredPerson(@Valid @RequestBody final InjuredPersonRO injuredPersonRO) {
		return _injuredPersonRestService.createInjuredPerson(injuredPersonRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_INJURED_PERSONS, method = RequestMethod.POST)
	public Set<InjuredPersonRO> createInjuredPersons(@Valid @RequestBody final InjuredPersonWrapper injuredPersonWrapper) {
		return _injuredPersonRestService.createInjuredPersons(injuredPersonWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_INJURED_PERSON, method = RequestMethod.PUT)
	public InjuredPersonRO updateInjuredPerson(@Valid @RequestBody final InjuredPersonRO injuredPersonRO) {
		return _injuredPersonRestService.updateInjuredPerson(injuredPersonRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_INJURED_PERSONS, method = RequestMethod.PUT)
	public Set<InjuredPersonRO> updateInjuredPersons(@Valid @RequestBody final InjuredPersonWrapper injuredPersonWrapper) {
		return _injuredPersonRestService.updateInjuredPersons(injuredPersonWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_DISTINGUISHING_FEATURE_DETAILS_FROM_INJURED_PERSON, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeDistinguishingFeatureDetailsFromInjuredPerson(@Valid @RequestBody final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper) {
		_injuredPersonRestService.removeDistinguishingFeatureDetailsFromInjuredPerson(distinguishingFeatureDetailWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_BODY_PARTS_FROM_INJURED_PERSON, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeBodyPartsFromInjuredPerson(@Valid @RequestBody final BodyPartWrapper bodyPartWrapper) {
		_injuredPersonRestService.removeBodyPartsFromInjuredPerson(bodyPartWrapper);
	}	
}
