package com.i2g.rms.rest.service.lookup;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.lookup.SuspectLookup;
import com.i2g.rms.rest.model.lookup.SuspectLookupRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.service.lookup.SuspectLookupService;

/**
 * Rest services for Suspect lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class SuspectLookupRestServiceImpl extends AbstractRestService implements SuspectLookupRestService {

	private final Logger _logger = LoggerFactory.getLogger(SuspectLookupRestServiceImpl.class);

	@Autowired
	private SuspectLookupService _suspectLookupService;

	@Override
	@Transactional(readOnly = true)
	public List<SuspectLookupRO> get() {
		List<SuspectLookup> suspectLookups = _suspectLookupService.get();
		List<SuspectLookupRO> suspectLookupROs = suspectLookups.isEmpty() ? Collections.emptyList()
				: _mapperService.map(suspectLookups, SuspectLookupRO.class);
		return suspectLookupROs;
	}
}
