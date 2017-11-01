package com.i2g.rms.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.Claim;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.ClaimRO;
import com.i2g.rms.rest.model.wrapper.ClaimWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.ClaimRestService;

/**
 * Rest controller for claim read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class ClaimController extends AbstractRestController {
	
	@Autowired
	private ClaimRestService _claimRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_CLAIMS, method = RequestMethod.GET)
	@Searchable(sourceType = ClaimRO.class, value = Claim.class)
	public List<ClaimRO> get() {
		return _claimRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_CLAIMS_HANDLED_BY_CLAIM_HANDLER_LOGIN_ID, method = RequestMethod.GET)
	@Searchable(sourceType = ClaimRO.class, value = Claim.class)
	public List<ClaimRO> get(@PathVariable final String claimHandlerLoginId) {
		return _claimRestService.get(claimHandlerLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_CLAIM_BY_CLAIM_ID, method = RequestMethod.GET)
	public ClaimRO get(@PathVariable final Long claimId) {
		return _claimRestService.get(claimId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_CLAIM_BY_INCIDENT_ID, method = RequestMethod.GET)
	public ClaimRO getClaimByIncidentId(@PathVariable final Long incidentId) {
		return _claimRestService.getClaimByIncidentId(incidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_CLAIM_BY_UNIQUE_INCIDENT_ID, method = RequestMethod.GET)
	public ClaimRO getClaimByUniqueIncidentId(@PathVariable final String uniqueIncidentId) {
		return _claimRestService.getClaimByUniqueIncidentId(uniqueIncidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_CLAIM, method = RequestMethod.POST)
	public ClaimRO createClaim(@Valid @RequestBody final ClaimRO claimRO) {
		return _claimRestService.createClaim(claimRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_CLAIM, method = RequestMethod.PUT)
	public ClaimRO updateClaim(@Valid @RequestBody final ClaimRO claimRO) {
		return _claimRestService.updateClaim(claimRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_OR_UPDATE_CLAIM, method = RequestMethod.PUT)
	public ClaimRO addOrUpdateClaim(@Valid @RequestBody final ClaimRO claimRO) {
		return _claimRestService.addOrUpdateClaim(claimRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_CLAIM, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClaim(@PathVariable final Long claimId) {
		_claimRestService.deleteClaim(claimId);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_CLAIMS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClaims(@Valid @RequestBody final ClaimWrapper claimWrapper) {
		_claimRestService.deleteClaims(claimWrapper);
	}
}
