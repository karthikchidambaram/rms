package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Crime;
import com.i2g.rms.persistence.dao.CrimeDao;

/**
 * Back-end service for crime related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class CrimeServiceImpl extends AbstractService implements CrimeService {

	@Autowired
	private CrimeDao _crimeDao;

	private CrimeServiceImpl() {
	}

	@Override
	public List<Crime> get() {
		return _crimeDao.get();
	}

	@Override
	public Crime get(final long id) {
		return _crimeDao.get(id);
	}

	@Override
	public Crime createCrime(final Crime crime) {
		return _crimeDao.createCrime(crime);
	}

	@Override
	public Crime updateCrime(final Crime crime) {
		return _crimeDao.updateCrime(crime);
	}
}
