package com.i2g.rms.rest.service.incident;

import java.util.List;

import com.i2g.rms.rest.model.incident.SearchIncidentRO;

/**
 * Rest Service for search incident.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface SearchIncidentRestService {

	public List<SearchIncidentRO> get();	

}
