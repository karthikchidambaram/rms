package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.Department;
import com.i2g.rms.domain.model.tablemaintenance.Organization;
import com.i2g.rms.domain.model.tablemaintenance.Position;
import com.i2g.rms.domain.model.tablemaintenance.PositionLevel;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface PositionDao {
	
	public List<Position> get();
	
	public List<Position> get(final Organization organization);
	
	public List<Position> get(final Department department);
	
	public List<Position> get(final PositionLevel positionLevel);

	public Position getByCode(final String code);

	public Position create(final String code, final String description, final PositionLevel positionLevel, final Organization organization);
	
	public Position create(final String code, final String description, final PositionLevel positionLevel, final Department department);
	
	public Position create(final String code, final String description, final Organization organization);
	
	public Position create(final String code, final String description, final Department department);

	public Position update(final String code, final String description);

	public void delete(final String code);
	
}
