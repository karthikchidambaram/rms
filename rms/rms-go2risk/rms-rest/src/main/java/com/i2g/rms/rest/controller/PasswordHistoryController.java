package com.i2g.rms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.PasswordHistory;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.PasswordHistoryRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.PasswordHistoryRestService;

/**
 * Rest controller for password history read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class PasswordHistoryController extends AbstractRestController {

	@Autowired
	private PasswordHistoryRestService _passwordHistoryRestService;

	@RequestMapping(value = RequestMappingConstants.GET_ALL_PASSWORD_HISTORIES, method = RequestMethod.GET)
	@Searchable(sourceType = PasswordHistoryRO.class, value = PasswordHistory.class)
	public List<PasswordHistoryRO> getPasswordHistory() {
		return _passwordHistoryRestService.getPasswordHistory();
	}
}
