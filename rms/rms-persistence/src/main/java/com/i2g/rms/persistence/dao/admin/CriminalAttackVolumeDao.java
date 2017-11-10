package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.CriminalAttackVolume;

/**
 * Back-end DAO for Admin dashboard criminal attack incident volume
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface CriminalAttackVolumeDao {

	public List<CriminalAttackVolume> get();
	
}
