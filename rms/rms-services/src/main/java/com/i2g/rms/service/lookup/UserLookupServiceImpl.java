package com.i2g.rms.service.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.lookup.UserLookup;
import com.i2g.rms.persistence.dao.lookup.UserLookupDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for user lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class UserLookupServiceImpl extends AbstractService implements UserLookupService {

	@Autowired
	private UserLookupDao _userLookupDao;

	private UserLookupServiceImpl() {
	}

	@Override
	public List<UserLookup> get() {
		return _userLookupDao.get();
	}
}
