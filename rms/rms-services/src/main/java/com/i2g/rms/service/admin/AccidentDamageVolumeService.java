package com.i2g.rms.service.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AccidentDamageVolume;

/**
 * Service interface for admin dashboard accident damage incident volume graph
 * data.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AccidentDamageVolumeService {

	public List<AccidentDamageVolume> get();

}
