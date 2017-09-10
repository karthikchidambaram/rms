package com.i2g.rms.rest.service.lookup;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.lookup.WitnessLookup;
import com.i2g.rms.rest.model.lookup.WitnessLookupRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.service.lookup.WitnessLookupService;

/**
 * Rest services for Witness lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class WitnessLookupRestServiceImpl extends AbstractRestService implements WitnessLookupRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(WitnessLookupRestServiceImpl.class);

	@Autowired
	private WitnessLookupService _witnessLookupService;

	@Override
	@Transactional(readOnly = true)
	public List<WitnessLookupRO> get() {
		List<WitnessLookup> witnessLookups = _witnessLookupService.get();
		List<WitnessLookupRO> witnessLookupROs = witnessLookups.isEmpty() ? Collections.emptyList()
				: _mapperService.map(witnessLookups, WitnessLookupRO.class);
		return witnessLookupROs;
	}
}
