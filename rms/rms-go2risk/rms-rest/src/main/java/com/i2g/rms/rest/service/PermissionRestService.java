package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.PermissionRO;

/**
 * Rest Service Interface for permissions rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface PermissionRestService {
	public List<PermissionRO> getPermissions();
}
