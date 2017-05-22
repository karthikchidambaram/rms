package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.GroupRO;

/**
 * Rest Service Interface for group rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface GroupRestService {
	public List<GroupRO> getGroups();
}
