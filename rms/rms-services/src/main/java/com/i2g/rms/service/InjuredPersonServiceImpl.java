package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.persistence.dao.InjuredPersonDao;

/**
 * Back-end service for injured person related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class InjuredPersonServiceImpl extends AbstractService implements InjuredPersonService {

	@Autowired
	private InjuredPersonDao _injuredPersonDao;

	private InjuredPersonServiceImpl() {
	}

	@Override
	public List<InjuredPerson> get() {
		return _injuredPersonDao.get();
	}

	@Override
	public InjuredPerson get(final long id) {
		return _injuredPersonDao.get(id);
	}

	@Override
	public Set<InjuredPerson> createNewInjuredPersons(final List<InjuredPerson> injuredPersons) {
		return _injuredPersonDao.createNewInjuredPersons(injuredPersons);
	}	
}
