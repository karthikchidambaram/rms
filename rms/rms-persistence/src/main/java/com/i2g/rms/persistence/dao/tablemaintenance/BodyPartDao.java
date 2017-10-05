package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.BodyPart;
import com.i2g.rms.domain.model.tablemaintenance.BodyPart.BodyPartFrontOrBack;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface BodyPartDao {
	
	public List<BodyPart> get();

	public BodyPart getByCode(final String code);

	public BodyPart create(final String code, final String description);

	public BodyPart update(final String code, final String description);
	
	public BodyPart create(final String code, final String description, final BodyPartFrontOrBack bodyPartFrontOrBack);

	public BodyPart update(final String code, final String description, final BodyPartFrontOrBack bodyPartFrontOrBack);

	public void delete(final String code);
	
}
