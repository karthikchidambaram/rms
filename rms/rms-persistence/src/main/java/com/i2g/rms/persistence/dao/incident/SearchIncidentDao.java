package com.i2g.rms.persistence.dao.incident;

import java.util.List;

import com.i2g.rms.domain.model.incident.SearchIncident;

public interface SearchIncidentDao {
	
	public List<SearchIncident> get();	
	
}
