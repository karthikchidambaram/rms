package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.Group;

/**
 * Service interface for all group related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface GroupService {
	
	public List<Group> getGroups();
	
}
