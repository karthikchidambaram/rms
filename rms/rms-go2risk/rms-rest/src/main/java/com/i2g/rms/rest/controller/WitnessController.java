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

import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.lookup.WitnessTableRO;
import com.i2g.rms.rest.model.wrapper.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.model.wrapper.WitnessWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.WitnessRestService;

/**
 * Rest controller for witness read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class WitnessController extends AbstractRestController {
	
	@Autowired
	private WitnessRestService _witnessRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_WITNESSES, method = RequestMethod.GET)
	@Searchable(sourceType = WitnessRO.class, value = Witness.class)
	public List<WitnessRO> get() {
		return _witnessRestService.get();		
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_WITNESS_TABLE_BY_ACCIDENT_ID, method = RequestMethod.GET)
	public List<WitnessTableRO> getWitnessTableByAccidentId(@PathVariable final Long accidentId) {
		return _witnessRestService.getWitnessTableByAccidentId(accidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_WITNESS_TABLE_BY_CRIME_ID, method = RequestMethod.GET)
	public List<WitnessTableRO> getWitnessTableByCrimeId(@PathVariable final Long crimeId) {
		return _witnessRestService.getWitnessTableByCrimeId(crimeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_WITNESS_BY_WITNESS_ID, method = RequestMethod.GET)
	public WitnessRO get(@PathVariable final Long witnessId) {
		return _witnessRestService.get(witnessId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_WITNESS, method = RequestMethod.POST)
	public WitnessRO createWitness(@Valid @RequestBody final WitnessRO witnessRO) {
		return _witnessRestService.createWitness(witnessRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_WITNESSES, method = RequestMethod.POST)
	public Set<WitnessRO> createWitnesses(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _witnessRestService.createWitnesses(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_WITNESS, method = RequestMethod.PUT)
	public WitnessRO updateWitness(@Valid @RequestBody final WitnessRO witnessRO) {
		return _witnessRestService.udpateWitness(witnessRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_WITNESSES, method = RequestMethod.PUT)
	public Set<WitnessRO> updateWitnesses(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _witnessRestService.udpateWitnesses(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_DISTINGUISHING_FEATURE_DETAILS_FROM_WITNESS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeDistinguishingFeatureDetailsFromWitness(@Valid @RequestBody final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper) {
		_witnessRestService.removeDistinguishingFeatureDetailsFromWitness(distinguishingFeatureDetailWrapper);
	}
}
