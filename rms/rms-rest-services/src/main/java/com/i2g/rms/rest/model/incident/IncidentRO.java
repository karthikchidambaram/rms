package com.i2g.rms.rest.model.incident;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.AccidentRO;
import com.i2g.rms.rest.model.AssetRO;
import com.i2g.rms.rest.model.ClaimRO;
import com.i2g.rms.rest.model.CrimeRO;
import com.i2g.rms.rest.model.DocumentViewRO;
import com.i2g.rms.rest.model.InvestigationRO;
import com.i2g.rms.rest.model.OfficeAddressRO;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.StatusFlagRO;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.YesNoTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentLocationDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentLocationRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentTypeRO;

/**
 * REST Object for returning password history details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentRO extends AbstractEntityRO {

	private long _id;
	private String _uniqueIncidentId;
	private LocalDateTime _incidentOpenedDateTime;
	private IncidentTypeRO _incidentType;
	private String _placeOfIncident;
	private EntryPointRO _entryPoint;
	private IncidentStatusRO _incidentStatus;
	private IncidentLocationRO _incidentLocation;
	private IncidentLocationDetailRO _incidentLocationDetail;
	private String _incidentDescription;
	private StatusFlagRO _statusFlag;
	private UserRO _incidentReportedBy;
	private LocalDateTime _incidentClosedDateTime;
	private Set<SuspectRO> _suspects = new HashSet<SuspectRO>(0);
	private Set<UserRO> _employeeSuspects = new HashSet<UserRO>(0);
	private YesNoTypeRO _assetDamage;
	private YesNoTypeRO _criminalAttack;
	private YesNoTypeRO _accidentDamage;
	private Set<ReportedLossRO> _reportedLosses = new HashSet<ReportedLossRO>(0);
	private AccidentRO _accident;
	private AssetRO _asset;
	private CrimeRO _crime;
	private String _incidentTypeOther;
	private String _entryPointOther;
	private String _incidentLocationOther;
	private OfficeAddressRO _officeAddress;
	private ClaimRO _claim;
	private YesNoTypeRO _notifyClaimsHandler;
	private YesNoTypeRO _showClaims;
	private YesNoTypeRO _showInvestigation;
	private InvestigationRO _investigation;
	private Set<DocumentViewRO> _documents = new HashSet<DocumentViewRO>(0);
	
	/**
	 * @return the id
	 */
	public long getId() {
		return _id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		_id = id;
	}

	/**
	 * @return the uniqueIncidentId
	 */
	public String getUniqueIncidentId() {
		return _uniqueIncidentId;
	}

	/**
	 * @param uniqueIncidentId
	 *            the uniqueIncidentId to set
	 */
	public void setUniqueIncidentId(final String uniqueIncidentId) {
		_uniqueIncidentId = uniqueIncidentId;
	}

	/**
	 * @return the incidentOpenedDateTime
	 */
	public LocalDateTime getIncidentOpenedDateTime() {
		return _incidentOpenedDateTime;
	}

	/**
	 * @param incidentOpenedDateTime
	 *            the incidentOpenedDateTime to set
	 */
	public void setIncidentOpenedDateTime(final LocalDateTime incidentOpenedDateTime) {
		_incidentOpenedDateTime = incidentOpenedDateTime;
	}

	/**
	 * @return the incidentType
	 */
	public IncidentTypeRO getIncidentType() {
		return _incidentType;
	}

	/**
	 * @param incidentType
	 *            the incidentType to set
	 */
	public void setIncidentType(final IncidentTypeRO incidentType) {
		_incidentType = incidentType;
	}

	/**
	 * @return the placeOfIncident
	 */
	public String getPlaceOfIncident() {
		return _placeOfIncident;
	}

	/**
	 * @param placeOfIncident
	 *            the placeOfIncident to set
	 */
	public void setPlaceOfIncident(final String placeOfIncident) {
		_placeOfIncident = placeOfIncident;
	}

	/**
	 * @return the entryPoint
	 */
	public EntryPointRO getEntryPoint() {
		return _entryPoint;
	}

	/**
	 * @param entryPoint
	 *            the entryPoint to set
	 */
	public void setEntryPoint(final EntryPointRO entryPoint) {
		_entryPoint = entryPoint;
	}

	/**
	 * @return the incidentStatus
	 */
	public IncidentStatusRO getIncidentStatus() {
		return _incidentStatus;
	}

	/**
	 * @param incidentStatus
	 *            the incidentStatus to set
	 */
	public void setIncidentStatus(final IncidentStatusRO incidentStatus) {
		_incidentStatus = incidentStatus;
	}

	/**
	 * @return the incidentLocationDetail
	 */
	public IncidentLocationDetailRO getIncidentLocationDetail() {
		return _incidentLocationDetail;
	}

	/**
	 * @param incidentLocationDetail
	 *            the incidentLocationDetail to set
	 */
	public void setIncidentLocationDetail(final IncidentLocationDetailRO incidentLocationDetail) {
		_incidentLocationDetail = incidentLocationDetail;
	}

	/**
	 * @return the incidentDescription
	 */
	public String getIncidentDescription() {
		return _incidentDescription;
	}

	/**
	 * @param incidentDescription
	 *            the incidentDescription to set
	 */
	public void setIncidentDescription(final String incidentDescription) {
		_incidentDescription = incidentDescription;
	}

	/**
	 * @return the statusFlag
	 */
	public StatusFlagRO getStatusFlag() {
		return _statusFlag;
	}

	/**
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(final StatusFlagRO statusFlag) {
		_statusFlag = statusFlag;
	}

	/**
	 * @return the user
	 */
	public UserRO getIncidentReportedBy() {
		return _incidentReportedBy;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setIncidentReportedBy(final UserRO incidentReportedBy) {
		_incidentReportedBy = incidentReportedBy;
	}

	/**
	 * @return the incidentClosedDateTime
	 */
	public LocalDateTime getIncidentClosedDateTime() {
		return _incidentClosedDateTime;
	}

	/**
	 * @param incidentClosedDateTime
	 *            the incidentClosedDateTime to set
	 */
	public void setIncidentClosedDateTime(final LocalDateTime incidentClosedDateTime) {
		_incidentClosedDateTime = incidentClosedDateTime;
	}

	/**
	 * @return the suspects
	 */
	public Set<SuspectRO> getSuspects() {
		return _suspects;
	}

	/**
	 * @param suspects
	 *            the suspects to set
	 */
	public void setSuspects(final Set<SuspectRO> suspects) {
		_suspects = suspects;
	}

	/**
	 * @return the employeeSuspects
	 */
	public Set<UserRO> getEmployeeSuspects() {
		return _employeeSuspects;
	}

	/**
	 * @param employeeSuspects
	 *            the employeeSuspects to set
	 */
	public void setEmployeeSuspects(final Set<UserRO> employeeSuspects) {
		_employeeSuspects = employeeSuspects;
	}

	public YesNoTypeRO getAssetDamage() {
		return _assetDamage;
	}

	public YesNoTypeRO getCriminalAttack() {
		return _criminalAttack;
	}

	public YesNoTypeRO getAccidentDamage() {
		return _accidentDamage;
	}

	public void setAssetDamage(final YesNoTypeRO assetDamage) {
		_assetDamage = assetDamage;
	}

	public void setCriminalAttack(final YesNoTypeRO criminalAttack) {
		_criminalAttack = criminalAttack;
	}

	public void setAccidentDamage(final YesNoTypeRO accidentDamage) {
		_accidentDamage = accidentDamage;
	}

	public Set<ReportedLossRO> getReportedLosses() {
		return _reportedLosses;
	}

	public void setReportedLosses(final Set<ReportedLossRO> reportedLosses) {
		_reportedLosses = reportedLosses;
	}

	/**
	 * @return the incidentLocation
	 */
	public IncidentLocationRO getIncidentLocation() {
		return _incidentLocation;
	}

	/**
	 * @param incidentLocation
	 *            the incidentLocation to set
	 */
	public void setIncidentLocation(final IncidentLocationRO incidentLocation) {
		_incidentLocation = incidentLocation;
	}

	/**
	 * @return the accident
	 */
	public AccidentRO getAccident() {
		return _accident;
	}

	/**
	 * @param accident
	 *            the accident to set
	 */
	public void setAccident(final AccidentRO accident) {
		_accident = accident;
	}

	/**
	 * @return the asset
	 */
	public AssetRO getAsset() {
		return _asset;
	}

	/**
	 * @param asset
	 *            the asset to set
	 */
	public void setAsset(final AssetRO asset) {
		_asset = asset;
	}

	/**
	 * @return the crime
	 */
	public CrimeRO getCrime() {
		return _crime;
	}

	/**
	 * @param crime
	 *            the crime to set
	 */
	public void setCrime(final CrimeRO crime) {
		_crime = crime;
	}

	/**
	 * @return the incidentTypeOther
	 */
	public String getIncidentTypeOther() {
		return _incidentTypeOther;
	}

	/**
	 * @param incidentTypeOther
	 *            the incidentTypeOther to set
	 */
	public void setIncidentTypeOther(final String incidentTypeOther) {
		_incidentTypeOther = incidentTypeOther;
	}

	/**
	 * @return the entryPointOther
	 */
	public String getEntryPointOther() {
		return _entryPointOther;
	}

	/**
	 * @param entryPointOther
	 *            the entryPointOther to set
	 */
	public void setEntryPointOther(final String entryPointOther) {
		_entryPointOther = entryPointOther;
	}

	/**
	 * @return the incidentLocationOther
	 */
	public String getIncidentLocationOther() {
		return _incidentLocationOther;
	}

	/**
	 * @param incidentLocationOther
	 *            the incidentLocationOther to set
	 */
	public void setIncidentLocationOther(final String incidentLocationOther) {
		_incidentLocationOther = incidentLocationOther;
	}

	public OfficeAddressRO getOfficeAddress() {
		return _officeAddress;
	}

	public void setOfficeAddress(final OfficeAddressRO officeAddress) {
		_officeAddress = officeAddress;
	}

	public ClaimRO getClaim() {
		return _claim;
	}

	public void setClaim(final ClaimRO claim) {
		_claim = claim;
	}

	public YesNoTypeRO getNotifyClaimsHandler() {
		return _notifyClaimsHandler;
	}

	public void setNotifyClaimsHandler(final YesNoTypeRO notifyClaimsHandler) {
		_notifyClaimsHandler = notifyClaimsHandler;
	}

	public YesNoTypeRO getShowClaims() {
		return _showClaims;
	}

	public void setShowClaims(final YesNoTypeRO showClaims) {
		_showClaims = showClaims;
	}

	public YesNoTypeRO getShowInvestigation() {
		return _showInvestigation;
	}

	public void setShowInvestigation(final YesNoTypeRO showInvestigation) {
		_showInvestigation = showInvestigation;
	}

	public InvestigationRO getInvestigation() {
		return _investigation;
	}

	public void setInvestigation(final InvestigationRO investigation) {
		_investigation = investigation;
	}

	public Set<DocumentViewRO> getDocuments() {
		return _documents;
	}

	public void setDocuments(final Set<DocumentViewRO> documents) {
		_documents = documents;
	}	
}
