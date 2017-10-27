package com.i2g.rms.rest.model.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.EquipmentRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentWrapper {

	private Long assetId;
	private Long[] equipmentIds;
	private String[] uniqueEquipmentIds;
	private List<EquipmentRO> equipments = new ArrayList<EquipmentRO>(0);

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(final Long assetId) {
		this.assetId = assetId;
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

	public List<EquipmentRO> getEquipments() {
		return equipments;
	}

	public void setEquipments(final List<EquipmentRO> equipments) {
		this.equipments = equipments;
	}
}