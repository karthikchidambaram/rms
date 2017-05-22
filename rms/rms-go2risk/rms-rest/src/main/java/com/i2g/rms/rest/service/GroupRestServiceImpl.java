package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Group;
import com.i2g.rms.rest.model.GroupRO;
import com.i2g.rms.service.GroupService;

/**
 * Rest services for group rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class GroupRestServiceImpl extends AbstractRestService implements GroupRestService {
	
	private final Logger _logger = LoggerFactory.getLogger(GroupRestServiceImpl.class);
	
	@Autowired
	private GroupService _groupService;
	
	@Transactional(readOnly = true)
	public List<GroupRO> getGroups() {
		List<Group> groups = _groupService.getGroups();
		List<GroupRO> groupROs = groups.isEmpty() ? Collections.emptyList() : _mapperService.map(groups, GroupRO.class);
		return groupROs;
	}
}
