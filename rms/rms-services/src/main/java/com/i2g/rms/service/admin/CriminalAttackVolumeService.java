package com.i2g.rms.service.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.CriminalAttackVolume;

/**
 * Service interface for admin dashboard criminal attack incident volume graph
 * data.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface CriminalAttackVolumeService {

	public List<CriminalAttackVolume> get();

}
