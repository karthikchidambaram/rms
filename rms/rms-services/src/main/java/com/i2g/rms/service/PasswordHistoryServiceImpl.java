package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.PasswordHistory;
import com.i2g.rms.persistence.dao.PasswordHistoryDao;

/**
 * Back-end service for password history related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class PasswordHistoryServiceImpl extends AbstractService implements PasswordHistoryService {

	@Autowired
	private PasswordHistoryDao _passwordHistoryDao;

	private PasswordHistoryServiceImpl() {
	}

	@Override
	public List<PasswordHistory> getPasswordHistory() {
		return _passwordHistoryDao.getPasswordHistory();
	}
}
