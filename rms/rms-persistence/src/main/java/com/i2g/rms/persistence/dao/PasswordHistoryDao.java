package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.PasswordHistory;

/**
 * Back-end DAO for password history related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface PasswordHistoryDao {

	public List<PasswordHistory> getPasswordHistory();

}
