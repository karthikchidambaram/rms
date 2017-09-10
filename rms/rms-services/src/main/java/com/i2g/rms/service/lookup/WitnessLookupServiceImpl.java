package com.i2g.rms.service.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.lookup.WitnessLookup;
import com.i2g.rms.persistence.dao.lookup.WitnessLookupDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for witness lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class WitnessLookupServiceImpl extends AbstractService implements WitnessLookupService {

	@Autowired
	private WitnessLookupDao _witnessLookupDao;

	private WitnessLookupServiceImpl() {
	}

	@Override
	public List<WitnessLookup> get() {
		return _witnessLookupDao.get();
	}
}
