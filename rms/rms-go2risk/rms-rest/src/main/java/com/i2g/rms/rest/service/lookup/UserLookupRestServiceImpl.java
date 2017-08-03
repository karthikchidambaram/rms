package com.i2g.rms.rest.service.lookup;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.lookup.UserLookup;
import com.i2g.rms.rest.model.lookup.UserLookupRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.service.lookup.UserLookupService;

/**
 * Rest services for user lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class UserLookupRestServiceImpl extends AbstractRestService implements UserLookupRestService {

	private final Logger _logger = LoggerFactory.getLogger(UserLookupRestServiceImpl.class);

	@Autowired
	private UserLookupService _userLookupService;

	@Override
	@Transactional(readOnly = true)
	public List<UserLookupRO> get() {
		List<UserLookup> userLookups = _userLookupService.get();
		List<UserLookupRO> userLookupROs = userLookups.isEmpty() ? Collections.emptyList()
				: _mapperService.map(userLookups, UserLookupRO.class);
		return userLookupROs;
	}
}
