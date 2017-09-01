package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.persistence.dao.AddressDao;

/**
 * Back-end service for address related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AddressServiceImpl extends AbstractService implements AddressService {

	@Autowired
	private AddressDao _addressDao;

	private AddressServiceImpl() {
	}

	@Override
	public List<Address> get() {
		return _addressDao.get();
	}

	@Override
	public Address get(final long id) {
		return _addressDao.get(id);
	}

	@Override
	public Address create(Address address) {
		return _addressDao.create(address);
	}
}
