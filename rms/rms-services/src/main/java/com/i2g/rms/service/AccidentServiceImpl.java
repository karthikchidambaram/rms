package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Accident;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.persistence.dao.AccidentDao;

/**
 * Back-end service for accident related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AccidentServiceImpl extends AbstractService implements AccidentService {

	@Autowired
	private AccidentDao _accidentDao;

	private AccidentServiceImpl() {
	}

	@Override
	public List<Accident> get() {
		return _accidentDao.get();
	}

	@Override
	public Accident get(final long id) {
		return _accidentDao.get(id);
	}

	@Override
	public Accident createAccident(final Accident accident) {
		return _accidentDao.createAccident(accident);
	}

	@Override
	public Accident updateAccident(final Accident accident) {
		return _accidentDao.updateAccident(accident);
	}

	@Override
	public Accident get(Incident incident) {
		return _accidentDao.get(incident);
	}
}
