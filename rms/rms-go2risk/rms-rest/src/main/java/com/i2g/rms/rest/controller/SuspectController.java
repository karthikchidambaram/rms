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

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.SuspectWrapper;
import com.i2g.rms.rest.service.SuspectRestService;

/**
 * Rest controller for suspect read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class SuspectController extends AbstractRestController {
	
	@Autowired
	private SuspectRestService _suspectRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_SUSPECTS, method = RequestMethod.GET)
	public List<SuspectRO> get() {
		return _suspectRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_SUSPECT_BY_SUSPECT_ID, method = RequestMethod.GET)
	public SuspectRO get(@PathVariable final Long id) {
		return _suspectRestService.get(id);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_SUSPECT, method = RequestMethod.POST)
	public SuspectRO createSuspect(@Valid @RequestBody final SuspectRO suspectRO) {
		return _suspectRestService.createSuspect(suspectRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_SUSPECTS, method = RequestMethod.POST)
	public List<SuspectRO> createSuspects(@Valid @RequestBody final SuspectWrapper suspectWrapper) {
		return _suspectRestService.createSuspects(suspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_SUSPECT, method = RequestMethod.PUT)
	public SuspectRO updateSuspect(final @Valid @RequestBody SuspectRO suspectRO) {
		return _suspectRestService.udpateSuspect(suspectRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_SUSPECTS, method = RequestMethod.PUT)
	public List<SuspectRO> updateSuspects(final @Valid @RequestBody SuspectWrapper suspectWrapper) {
		return _suspectRestService.udpateSuspects(suspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_DISTINGUISHING_FEATURE_DETAILS_FROM_SUSPECT, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeDistinguishingFeatureDetailsFromSuspect(final @Valid @RequestBody DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper) {
		_suspectRestService.removeDistinguishingFeatureDetailsFromSuspect(distinguishingFeatureDetailWrapper);
	}
}
