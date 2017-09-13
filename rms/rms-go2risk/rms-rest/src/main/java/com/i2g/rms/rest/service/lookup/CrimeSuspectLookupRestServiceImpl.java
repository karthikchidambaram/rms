package com.i2g.rms.rest.service.lookup;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.lookup.CrimeSuspectLookup;
import com.i2g.rms.rest.model.lookup.CrimeSuspectLookupRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.service.lookup.CrimeSuspectLookupService;

/**
 * Rest services for Crime suspect lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class CrimeSuspectLookupRestServiceImpl extends AbstractRestService implements CrimeSuspectLookupRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(CrimeSuspectLookupRestServiceImpl.class);

	@Autowired
	private CrimeSuspectLookupService _crimeSuspectLookupService;

	@Override
	@Transactional(readOnly = true)
	public List<CrimeSuspectLookupRO> get() {
		List<CrimeSuspectLookup> crimeSuspectLookups = _crimeSuspectLookupService.get();
		List<CrimeSuspectLookupRO> crimeSuspectLookupROs = crimeSuspectLookups.isEmpty() ? Collections.emptyList()
				: _mapperService.map(crimeSuspectLookups, CrimeSuspectLookupRO.class);
		return crimeSuspectLookupROs;
	}
}
