package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.Group;

/**
 * Back-end DAO for group related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface GroupDao {

	public List<Group> getGroups();

}
