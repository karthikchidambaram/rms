package com.i2g.rms.rest.service.lookup;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Manager;
import com.i2g.rms.rest.model.ManagerRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.service.lookup.ManagerLookupService;

/**
 * Rest services for user lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class ManagerLookupRestServiceImpl extends AbstractRestService implements ManagerLookupRestService {

	private final Logger _logger = LoggerFactory.getLogger(ManagerLookupRestServiceImpl.class);

	@Autowired
	private ManagerLookupService _managerLookupService;

	@Override
	@Transactional(readOnly = true)
	public List<ManagerRO> get() {
		List<Manager> managers = _managerLookupService.get();
		List<ManagerRO> managerROs = managers.isEmpty() ? Collections.emptyList()
				: _mapperService.map(managers, ManagerRO.class);
		return managerROs;
	}
}
