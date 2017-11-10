package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AccidentDamageVolume;

/**
 * Back-end DAO for Admin dashboard Accident damage incident volume
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AccidentDamageVolumeDao {

	public List<AccidentDamageVolume> get();
	
}
