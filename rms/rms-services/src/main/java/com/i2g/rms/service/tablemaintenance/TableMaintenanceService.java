package com.i2g.rms.service.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.EntryPoint;

/**
 * Service interface for all table maintenance operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TableMaintenanceService {

	/** Methods related to Entry Point */
	public List<EntryPoint> getEntryPoints();
	public EntryPoint getEntryPointByCode(final String code);
	public EntryPoint createEntryPoint(final String code, final String description);
	public EntryPoint updateEntryPoint(final String code, final String description);
	public void deleteEntryPoint(final String code);

}
