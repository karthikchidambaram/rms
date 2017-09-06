package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Accident;
import com.i2g.rms.rest.model.AccidentRO;
import com.i2g.rms.service.AccidentService;

/**
 * Rest services for accident rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AccidentRestServiceImpl extends AbstractRestService implements AccidentRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(AccidentRestServiceImpl.class);

	@Autowired
	private AccidentService _accidentService;
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AccidentRO> get() {
		List<Accident> accidents = _accidentService.get();
		List<AccidentRO> accidentROs = accidents.isEmpty() ? Collections.emptyList()
				: _mapperService.map(accidents, AccidentRO.class);
		return accidentROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public AccidentRO get(final long id) {
		if (id > 0) {
			return _mapperService.map(_accidentService.get(id), AccidentRO.class);
		} else {
			return null;
		}
	}
}
