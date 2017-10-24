package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.tablemaintenance.BodyPart;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
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
	public InjuredPerson createInjuredPerson(final InjuredPerson injuredPerson) {
		return _injuredPersonDao.createInjuredPerson(injuredPerson);
	}

	@Override
	public Set<InjuredPerson> createInjuredPersons(final Set<InjuredPerson> injuredPersons) {
		return _injuredPersonDao.createInjuredPersons(injuredPersons);
	}

	@Override
	public InjuredPerson updateInjuredPerson(final InjuredPerson injuredPerson) {
		return _injuredPersonDao.updateInjuredPerson(injuredPerson);
	}

	@Override
	public Set<InjuredPerson> updateInjuredPersons(final Set<InjuredPerson> injuredPersons) {
		return _injuredPersonDao.updateInjuredPersons(injuredPersons);
	}

	@Override
	public void removeDistinguishingFeatureDetailsFromInjuredPerson(final InjuredPerson injuredPerson, final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails) {
		_injuredPersonDao.removeDistinguishingFeatureDetailsFromInjuredPerson(injuredPerson, distinguishingFeatureDetails);
	}

	@Override
	public void removeBodyPartsFromInjuredPerson(final InjuredPerson injuredPerson, final Set<BodyPart> bodyParts) {
		_injuredPersonDao.removeBodyPartsFromInjuredPerson(injuredPerson, bodyParts); 
	}

}
