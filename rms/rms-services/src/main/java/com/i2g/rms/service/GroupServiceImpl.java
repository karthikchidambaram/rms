package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Group;
import com.i2g.rms.persistence.dao.GroupDao;

/**
 * Back-end service for group related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class GroupServiceImpl extends AbstractService implements GroupService {

	@Autowired
	private GroupDao _groupDao;

	private GroupServiceImpl() {
	}

	@Override
	public List<Group> getGroups() {
		return _groupDao.getGroups();
	}
}
