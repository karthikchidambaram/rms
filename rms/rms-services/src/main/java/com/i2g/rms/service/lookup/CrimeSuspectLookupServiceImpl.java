package com.i2g.rms.service.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.lookup.CrimeSuspectLookup;
import com.i2g.rms.persistence.dao.lookup.CrimeSuspectLookupDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for Crime suspect lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class CrimeSuspectLookupServiceImpl extends AbstractService implements CrimeSuspectLookupService {

	@Autowired
	private CrimeSuspectLookupDao _crimeSuspectLookupDao;

	private CrimeSuspectLookupServiceImpl() {
	}

	@Override
	public List<CrimeSuspectLookup> get() {
		return _crimeSuspectLookupDao.get();
	}
}
