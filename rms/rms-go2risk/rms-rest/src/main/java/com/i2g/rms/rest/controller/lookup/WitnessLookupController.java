package com.i2g.rms.rest.controller.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.lookup.WitnessLookup;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.lookup.WitnessLookupRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.lookup.WitnessLookupRestService;

/**
 * Rest controller for witness lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class WitnessLookupController extends AbstractRestController {

	@Autowired
	private WitnessLookupRestService _witnessLookupRestService;

	@RequestMapping(value = RequestMappingConstants.WITNESS_LOOKUP, method = RequestMethod.GET)
	@Searchable(sourceType = WitnessLookupRO.class, value = WitnessLookup.class)
	public List<WitnessLookupRO> get() {
		return _witnessLookupRestService.get();
	}
}
