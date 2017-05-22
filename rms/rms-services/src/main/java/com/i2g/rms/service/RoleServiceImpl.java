package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Role;
import com.i2g.rms.persistence.dao.RoleDao;

/**
 * Back-end service for role related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class RoleServiceImpl extends AbstractService implements RoleService {

	@Autowired
	private RoleDao _roleDao;

	private RoleServiceImpl() {
	}

	@Override
	public List<Role> getRoles() {
		return _roleDao.getRoles();
	}
}
