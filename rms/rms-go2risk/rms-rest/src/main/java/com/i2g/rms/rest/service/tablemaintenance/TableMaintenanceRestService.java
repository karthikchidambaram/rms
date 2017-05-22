package com.i2g.rms.rest.service.tablemaintenance;

import java.util.List;

import com.i2g.rms.rest.model.tablemaintenance.EntryPointRO;

/**
 * Rest Service Interface for all table maintenance objects.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TableMaintenanceRestService {
	
	/** Methods related to Entry Point */
	public List<EntryPointRO> getEntryPoints();
	public EntryPointRO getEntryPointByCode(final String code);
	public EntryPointRO createEntryPoint(final EntryPointRO entryPointRO);
	public EntryPointRO updateEntryPoint(final EntryPointRO entryPointRO);
	public void deleteEntryPoint(final String code);
	
}
