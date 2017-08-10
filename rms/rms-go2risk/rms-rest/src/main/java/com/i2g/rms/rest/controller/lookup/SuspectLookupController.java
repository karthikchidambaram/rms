package com.i2g.rms.rest.controller.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.lookup.SuspectLookup;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.lookup.SuspectLookupRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.lookup.SuspectLookupRestService;

/**
 * Rest controller for Suspect lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class SuspectLookupController extends AbstractRestController {

	@Autowired
	private SuspectLookupRestService _suspectLookupRestService;

	@RequestMapping(value = RequestMappingConstants.SUSPECT_LOOKUP, method = RequestMethod.GET)
	@Searchable(sourceType = SuspectLookupRO.class, value = SuspectLookup.class)
	public List<SuspectLookupRO> get() {
		return _suspectLookupRestService.get();
	}
}
