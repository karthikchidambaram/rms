package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Role;
import com.i2g.rms.rest.model.RoleRO;
import com.i2g.rms.service.RoleService;

/**
 * Rest services for role rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class RoleRestServiceImpl extends AbstractRestService implements RoleRestService {
	
	private final Logger _logger = LoggerFactory.getLogger(RoleRestServiceImpl.class);
	
	@Autowired
	private RoleService _roleService;
	
	@Transactional(readOnly = true)
	public List<RoleRO> getRoles() {
		List<Role> roles = _roleService.getRoles();
		List<RoleRO> roleROs = roles.isEmpty() ? Collections.emptyList() : _mapperService.map(roles, RoleRO.class);
		return roleROs;
	}
}
