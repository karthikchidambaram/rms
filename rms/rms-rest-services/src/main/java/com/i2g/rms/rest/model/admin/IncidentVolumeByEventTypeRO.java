package com.i2g.rms.rest.model.admin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * REST Object for Admin dashboard asset damage incident volume RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentVolumeByEventTypeRO {

	private List<CriminalAttackVolumeRO> criminalAttackVolumes = new ArrayList<CriminalAttackVolumeRO>(0);
	private List<AssetDamageVolumeRO> assetDamageVolumes = new ArrayList<AssetDamageVolumeRO>(0);
	private List<AccidentDamageVolumeRO> accidentDamageVolumes = new ArrayList<AccidentDamageVolumeRO>(0);

	public List<CriminalAttackVolumeRO> getCriminalAttackVolumes() {
		return criminalAttackVolumes;
	}

	public void setCriminalAttackVolumes(final List<CriminalAttackVolumeRO> criminalAttackVolumes) {
		this.criminalAttackVolumes = criminalAttackVolumes;
	}

	public List<AssetDamageVolumeRO> getAssetDamageVolumes() {
		return assetDamageVolumes;
	}

	public void setAssetDamageVolumes(final List<AssetDamageVolumeRO> assetDamageVolumes) {
		this.assetDamageVolumes = assetDamageVolumes;
	}

	public List<AccidentDamageVolumeRO> getAccidentDamageVolumes() {
		return accidentDamageVolumes;
	}

	public void setAccidentDamageVolumes(final List<AccidentDamageVolumeRO> accidentDamageVolumes) {
		this.accidentDamageVolumes = accidentDamageVolumes;
	}
}
