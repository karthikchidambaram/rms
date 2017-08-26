package com.i2g.rms.service.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Manager;
import com.i2g.rms.persistence.dao.lookup.ManagerLookupDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for manager lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class ManagerLookupServiceImpl extends AbstractService implements ManagerLookupService {

	@Autowired
	private ManagerLookupDao _managerLookupDao;

	private ManagerLookupServiceImpl() {
	}

	@Override
	public List<Manager> get() {
		return _managerLookupDao.get();
	}
}
