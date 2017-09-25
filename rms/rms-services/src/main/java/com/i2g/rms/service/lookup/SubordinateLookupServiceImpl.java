package com.i2g.rms.service.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Subordinate;
import com.i2g.rms.persistence.dao.lookup.SubordinateLookupDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for subordinate lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class SubordinateLookupServiceImpl extends AbstractService implements SubordinateLookupService {

	@Autowired
	private SubordinateLookupDao _subordinateLookupDao;

	private SubordinateLookupServiceImpl() {
	}

	@Override
	public List<Subordinate> get() {
		return _subordinateLookupDao.get();
	}

	@Override
	public List<Subordinate> get(final String managerLoginId) {
		return _subordinateLookupDao.get(managerLoginId);
	}
}
