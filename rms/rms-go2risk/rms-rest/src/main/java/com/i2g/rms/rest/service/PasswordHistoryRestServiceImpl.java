package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.PasswordHistory;
import com.i2g.rms.rest.model.PasswordHistoryRO;
import com.i2g.rms.service.PasswordHistoryService;

/**
 * Rest services for password history rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class PasswordHistoryRestServiceImpl extends AbstractRestService implements PasswordHistoryRestService {
	
	private final Logger _logger = LoggerFactory.getLogger(PasswordHistoryRestServiceImpl.class);
	
	@Autowired
	private PasswordHistoryService _passwordHistoryService;
	
	@Transactional(readOnly = true)
	public List<PasswordHistoryRO> getPasswordHistory() {
		List<PasswordHistory> passwordHistory = _passwordHistoryService.getPasswordHistory();
		List<PasswordHistoryRO> passwordHistoryROs = (passwordHistory == null || passwordHistory.isEmpty()) ? Collections.emptyList() : _mapperService.map(passwordHistory, PasswordHistoryRO.class);
		return passwordHistoryROs;
	}
}
