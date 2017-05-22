package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.PasswordHistoryRO;

/**
 * Rest Service for password history rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface PasswordHistoryRestService {
	public List<PasswordHistoryRO> getPasswordHistory();
}
