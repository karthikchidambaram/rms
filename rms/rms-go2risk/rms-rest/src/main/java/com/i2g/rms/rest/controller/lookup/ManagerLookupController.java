package com.i2g.rms.rest.controller.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.Manager;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.ManagerRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.lookup.ManagerLookupRestService;

/**
 * Rest controller for user lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class ManagerLookupController extends AbstractRestController {

	@Autowired
	private ManagerLookupRestService _managerLookupRestService;

	@RequestMapping(value = RequestMappingConstants.MANAGER_LOOKUP, method = RequestMethod.GET)
	@Searchable(sourceType = ManagerRO.class, value = Manager.class)
	public List<ManagerRO> get() {
		return _managerLookupRestService.get();
	}
}
