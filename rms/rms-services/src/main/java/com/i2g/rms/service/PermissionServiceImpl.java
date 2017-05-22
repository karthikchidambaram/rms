package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Permission;
import com.i2g.rms.persistence.dao.PermissionDao;

/**
 * Back-end service for permissions related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class PermissionServiceImpl extends AbstractService implements PermissionService {

	@Autowired
	private PermissionDao _permissionDao;

	private PermissionServiceImpl() {
	}

	@Override
	public List<Permission> getPermissions() {
		return _permissionDao.getPermissions();
	}
}
