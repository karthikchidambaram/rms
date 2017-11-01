package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Permission;
import com.i2g.rms.rest.model.PermissionRO;
import com.i2g.rms.service.PermissionService;

/**
 * Rest services for permissions rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class PermissionRestServiceImpl extends AbstractRestService implements PermissionRestService {

	private final Logger _logger = LoggerFactory.getLogger(PermissionRestServiceImpl.class);

	@Autowired
	private PermissionService _permissionService;

	@Transactional(readOnly = true)
	public List<PermissionRO> getPermissions() {
		List<Permission> permissions = _permissionService.getPermissions();
		List<PermissionRO> permissionROs = (permissions == null || permissions.isEmpty()) ? Collections.emptyList() : _mapperService.map(permissions, PermissionRO.class);
		return permissionROs;
	}
}
