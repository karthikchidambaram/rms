package com.i2g.rms.service.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.lookup.InjuredPersonLookup;
import com.i2g.rms.persistence.dao.lookup.InjuredPersonLookupDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for injured person lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class InjuredPersonLookupServiceImpl extends AbstractService implements InjuredPersonLookupService {

	@Autowired
	private InjuredPersonLookupDao _injuredPersonLookupDao;

	private InjuredPersonLookupServiceImpl() {
	}

	@Override
	public List<InjuredPersonLookup> get() {
		return _injuredPersonLookupDao.get();
	}
}
