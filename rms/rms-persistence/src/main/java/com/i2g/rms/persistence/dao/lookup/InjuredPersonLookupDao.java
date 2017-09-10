package com.i2g.rms.persistence.dao.lookup;

import java.util.List;

import com.i2g.rms.domain.model.lookup.InjuredPersonLookup;

public interface InjuredPersonLookupDao {
	
	public List<InjuredPersonLookup> get();	
	
}
