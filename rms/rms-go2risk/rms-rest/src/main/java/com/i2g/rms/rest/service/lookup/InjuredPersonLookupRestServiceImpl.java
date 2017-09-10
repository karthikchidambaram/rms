package com.i2g.rms.rest.service.lookup;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.lookup.InjuredPersonLookup;
import com.i2g.rms.rest.model.lookup.InjuredPersonLookupRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.service.lookup.InjuredPersonLookupService;

/**
 * Rest services for injured person lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class InjuredPersonLookupRestServiceImpl extends AbstractRestService implements InjuredPersonLookupRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(InjuredPersonLookupRestServiceImpl.class);

	@Autowired
	private InjuredPersonLookupService _injuredPersonLookupService;

	@Override
	@Transactional(readOnly = true)
	public List<InjuredPersonLookupRO> get() {
		List<InjuredPersonLookup> injuredPersonLookups = _injuredPersonLookupService.get();
		List<InjuredPersonLookupRO> injuredPersonLookupROs = injuredPersonLookups.isEmpty() ? Collections.emptyList()
				: _mapperService.map(injuredPersonLookups, InjuredPersonLookupRO.class);
		return injuredPersonLookupROs;
	}
}
