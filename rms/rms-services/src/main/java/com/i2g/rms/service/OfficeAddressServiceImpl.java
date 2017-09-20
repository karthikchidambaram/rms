package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.OfficeAddress;
import com.i2g.rms.persistence.dao.OfficeAddressDao;

/**
 * Back-end service for Office address related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class OfficeAddressServiceImpl extends AbstractService implements OfficeAddressService {

	@Autowired
	private OfficeAddressDao _officeAddressDao;

	private OfficeAddressServiceImpl() {
	}

	@Override
	public List<OfficeAddress> get() {
		return _officeAddressDao.get();
	}

	@Override
	public OfficeAddress get(final long id) {
		return _officeAddressDao.get(id);
	}

	@Override
	public OfficeAddress create(final OfficeAddress officeAddress) {
		return _officeAddressDao.create(officeAddress);
	}
}
