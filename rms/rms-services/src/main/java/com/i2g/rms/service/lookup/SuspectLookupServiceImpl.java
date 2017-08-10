package com.i2g.rms.service.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.lookup.SuspectLookup;
import com.i2g.rms.persistence.dao.lookup.SuspectLookupDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for Suspect lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class SuspectLookupServiceImpl extends AbstractService implements SuspectLookupService {

	@Autowired
	private SuspectLookupDao _suspectLookupDao;

	private SuspectLookupServiceImpl() {
	}

	@Override
	public List<SuspectLookup> get() {
		return _suspectLookupDao.get();
	}
}
