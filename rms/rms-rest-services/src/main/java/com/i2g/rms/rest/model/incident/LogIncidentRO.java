package com.i2g.rms.rest.model.incident;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.YesNoTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentLocationDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentLocationRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentTypeRO;

/**
 * Search Incident Details RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogIncidentRO extends AbstractEntityRO {

	private long incidentId;

	private LocalDateTime incidentOpenedDateTime;

	private String uniqueIncidentId;
	private String placeOfIncident;
	private String landmark;
	private String incidentDescription;

	private EntryPointRO entryPoint;
	private IncidentStatusRO incidentStatus;
	private IncidentLocationRO incidentLocation;
	private IncidentLocationDetailRO incidentLocationDetail;
	private IncidentTypeRO incidentType;

	private YesNoTypeRO assetDamage;
	private YesNoTypeRO criminalAttack;
	private YesNoTypeRO accidentDamage;

	private String incidentTypeOther;
	private String entryPointOther;
	private String incidentLocationOther;

	public long getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(final long incidentId) {
		this.incidentId = incidentId;
	}

	public String getUniqueIncidentId() {
		return uniqueIncidentId;
	}

	public void setUniqueIncidentId(final String uniqueIncidentId) {
		this.uniqueIncidentId = uniqueIncidentId;
	}

	public LocalDateTime getIncidentOpenedDateTime() {
		return incidentOpenedDateTime;
	}

	public void setIncidentOpenedDateTime(final LocalDateTime incidentOpenedDateTime) {
		this.incidentOpenedDateTime = incidentOpenedDateTime;
	}

	public String getPlaceOfIncident() {
		return placeOfIncident;
	}

	public void setPlaceOfIncident(final String placeOfIncident) {
		this.placeOfIncident = placeOfIncident;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(final String landmark) {
		this.landmark = landmark;
	}

	public EntryPointRO getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(final EntryPointRO entryPoint) {
		this.entryPoint = entryPoint;
	}

	public IncidentStatusRO getIncidentStatus() {
		return incidentStatus;
	}

	public void setIncidentStatus(final IncidentStatusRO incidentStatus) {
		this.incidentStatus = incidentStatus;
	}

	public IncidentLocationRO getIncidentLocation() {
		return incidentLocation;
	}

	public void setIncidentLocation(final IncidentLocationRO incidentLocation) {
		this.incidentLocation = incidentLocation;
	}

	public String getIncidentDescription() {
		return incidentDescription;
	}

	public void setIncidentDescription(final String incidentDescription) {
		this.incidentDescription = incidentDescription;
	}

	public IncidentTypeRO getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(final IncidentTypeRO incidentType) {
		this.incidentType = incidentType;
	}

	public YesNoTypeRO getAssetDamage() {
		return assetDamage;
	}

	public void setAssetDamage(final YesNoTypeRO assetDamage) {
		this.assetDamage = assetDamage;
	}

	public YesNoTypeRO getCriminalAttack() {
		return criminalAttack;
	}

	public void setCriminalAttack(final YesNoTypeRO criminalAttack) {
		this.criminalAttack = criminalAttack;
	}

	public YesNoTypeRO getAccidentDamage() {
		return accidentDamage;
	}

	public void setAccidentDamage(final YesNoTypeRO accidentDamage) {
		this.accidentDamage = accidentDamage;
	}

	public IncidentLocationDetailRO getIncidentLocationDetail() {
		return incidentLocationDetail;
	}

	public void setIncidentLocationDetail(final IncidentLocationDetailRO incidentLocationDetail) {
		this.incidentLocationDetail = incidentLocationDetail;
	}

	public String getIncidentTypeOther() {
		return incidentTypeOther;
	}

	public void setIncidentTypeOther(final String incidentTypeOther) {
		this.incidentTypeOther = incidentTypeOther;
	}

	public String getEntryPointOther() {
		return entryPointOther;
	}

	public void setEntryPointOther(final String entryPointOther) {
		this.entryPointOther = entryPointOther;
	}

	public String getIncidentLocationOther() {
		return incidentLocationOther;
	}

	public void setIncidentLocationOther(final String incidentLocationOther) {
		this.incidentLocationOther = incidentLocationOther;
	}
}
