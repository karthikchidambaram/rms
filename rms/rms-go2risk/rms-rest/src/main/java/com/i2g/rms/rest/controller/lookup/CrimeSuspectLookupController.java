package com.i2g.rms.rest.controller.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.lookup.CrimeSuspectLookup;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.lookup.CrimeSuspectLookupRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.lookup.CrimeSuspectLookupRestService;

/**
 * Rest controller for Crime suspect lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class CrimeSuspectLookupController extends AbstractRestController {

	@Autowired
	private CrimeSuspectLookupRestService _crimeSuspectLookupRestService;

	@RequestMapping(value = RequestMappingConstants.CRIME_SUSPECT_LOOKUP, method = RequestMethod.GET)
	@Searchable(sourceType = CrimeSuspectLookupRO.class, value = CrimeSuspectLookup.class)
	public List<CrimeSuspectLookupRO> get() {
		return _crimeSuspectLookupRestService.get();
	}
}
