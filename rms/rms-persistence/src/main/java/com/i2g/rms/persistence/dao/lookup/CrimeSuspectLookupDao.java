package com.i2g.rms.persistence.dao.lookup;

import java.util.List;

import com.i2g.rms.domain.model.lookup.CrimeSuspectLookup;

public interface CrimeSuspectLookupDao {
	
	public List<CrimeSuspectLookup> get();	
	
}
