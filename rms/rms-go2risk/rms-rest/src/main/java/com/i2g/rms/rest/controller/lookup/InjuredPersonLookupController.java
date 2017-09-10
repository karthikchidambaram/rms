package com.i2g.rms.rest.controller.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.lookup.InjuredPersonLookup;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.lookup.InjuredPersonLookupRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.lookup.InjuredPersonLookupRestService;

/**
 * Rest controller for injured person lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class InjuredPersonLookupController extends AbstractRestController {

	@Autowired
	private InjuredPersonLookupRestService _injuredPersonLookupRestService;

	@RequestMapping(value = RequestMappingConstants.INJURED_PERSON_LOOKUP, method = RequestMethod.GET)
	@Searchable(sourceType = InjuredPersonLookupRO.class, value = InjuredPersonLookup.class)
	public List<InjuredPersonLookupRO> get() {
		return _injuredPersonLookupRestService.get();
	}
}
