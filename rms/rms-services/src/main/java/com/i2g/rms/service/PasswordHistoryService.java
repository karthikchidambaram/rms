package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.PasswordHistory;

/**
 * Service interface for all password history related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface PasswordHistoryService {
	
	public List<PasswordHistory> getPasswordHistory();
	
}
