package com.i2g.rms.persistence.dao.lookup;

import java.util.List;

import com.i2g.rms.domain.model.Subordinate;

public interface SubordinateLookupDao {

	public List<Subordinate> get();

	public List<Subordinate> get(final String managerLoginId);

}
