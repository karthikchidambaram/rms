package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.EquipmentRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentWrapper {

	private Long assetId;
	private Long[] equipmentIds;
	private String[] uniqueEquipmentIds;
	private Set<EquipmentRO> equipments = new HashSet<EquipmentRO>(0);

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(final Long assetId) {
		if (assetId != null) {
			this.assetId = assetId;
		} else {
			this.assetId = 0l;
		}
	}

	public Long[] getEquipmentIds() {
		return equipmentIds;
	}

	public void setEquipmentIds(final Long[] equipmentIds) {
		this.equipmentIds = equipmentIds;
	}

	public String[] getUniqueEquipmentIds() {
		return uniqueEquipmentIds;
	}

	public void setUniqueEquipmentIds(final String[] uniqueEquipmentIds) {
		this.uniqueEquipmentIds = uniqueEquipmentIds;
	}

	public Set<EquipmentRO> getEquipments() {
		return equipments;
	}

	public void setEquipments(final Set<EquipmentRO> equipments) {
		this.equipments = equipments;
	}
}