package com.i2g.rms.service.tablemaintenance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.tablemaintenance.EntryPoint;
import com.i2g.rms.persistence.dao.tablemaintenance.EntryPointDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for all table maintenance operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TableMaintenaceServiceImpl extends AbstractService implements TableMaintenanceService {
	
	/** Inject the required table maintenance objects here */
	@Autowired
	private EntryPointDao _entryPointDao;
	

	/**
	 * Default constructor
	 */
	private TableMaintenaceServiceImpl() {
	}
	
	/** Methods related to Entry Point */
	@Override
	@Transactional
	public List<EntryPoint> getEntryPoints() {
		return _entryPointDao.get();
	}

	@Override
	@Transactional
	public EntryPoint getEntryPointByCode(final String code) {
		return _entryPointDao.getByCode(code);
	}

	@Override
	@Transactional
	public EntryPoint createEntryPoint(final String code, final String description) {
		return _entryPointDao.create(code, description);
	}

	@Override
	@Transactional
	public EntryPoint updateEntryPoint(final String code, final String description) {
		return _entryPointDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteEntryPoint(final String code) {
		_entryPointDao.delete(code);
	}
}
