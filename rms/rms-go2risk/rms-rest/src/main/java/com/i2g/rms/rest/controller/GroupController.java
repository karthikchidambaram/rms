package com.i2g.rms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.GroupRO;
import com.i2g.rms.rest.service.GroupRestService;

/**
 * Rest controller for group read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class GroupController extends AbstractRestController {

	@Autowired
	private GroupRestService _groupRestService;

	@RequestMapping(value = RequestMappingConstants.GET_ALL_GROUPS, method = RequestMethod.GET)
	public List<GroupRO> getGroups() {
		return _groupRestService.getGroups();
	}
}
