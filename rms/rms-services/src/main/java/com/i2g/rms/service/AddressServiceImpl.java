package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Witness;
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
	public Address create(final Address address) {
		return _addressDao.create(address);
	}

	@Override
	public Address updateAddress(final Address address) {
		return _addressDao.updateAddress(address);
	}

	@Override
	public List<Address> updateAddresses(final List<Address> addresses) {
		return _addressDao.updateAddresses(addresses);
	}

	@Override
	public List<Address> get(final User user) {
		return _addressDao.get(user);
	}

	@Override
	public List<Address> get(final Building building) {
		return _addressDao.get(building);
	}

	@Override
	public List<Address> get(final Witness witness) {
		return _addressDao.get(witness);
	}

	@Override
	public List<Address> get(final InjuredPerson injuredPerson) {
		return _addressDao.get(injuredPerson);
	}

	@Override
	public List<Address> get(final Suspect suspect) {
		return _addressDao.get(suspect);
	}

	@Override
	public List<Address> get(final CrimeSuspect crimeSuspect) {
		return _addressDao.get(crimeSuspect);
	}
}
