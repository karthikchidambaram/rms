package com.i2g.rms.rest.controller.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.lookup.UserLookup;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.lookup.UserLookupRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.lookup.UserLookupRestService;

/**
 * Rest controller for user lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class UserLookupController extends AbstractRestController {

	@Autowired
	private UserLookupRestService _userLookupRestService;

	@RequestMapping(value = RequestMappingConstants.USER_LOOKUP, method = RequestMethod.GET)
	@Searchable(sourceType = UserLookupRO.class, value = UserLookup.class)
	public List<UserLookupRO> get() {
		return _userLookupRestService.get();
	}
}
