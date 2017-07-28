package com.i2g.rms.util.tablemaintenance;

public enum TableMaintenanceTableNames {
	
	//All table maintenance table names are declared below:
	ACCIDENT_LOCATION("accident-location"),
	ACCIDENT_LOCATION_DETAIL("accident-location-detail"),
	ASSET_CATEGORY("asset-category"),
	CLAIM_REQUEST_REGISTRATION_TYPE("claim-request-registration-type"),
	CLAIM_STATUS("claim-status"),
	CLAIM_TYPE("claim-type"),
	DISTINGUISHING_FEATURE("distinguishing-feature"),
	DISTINGUISHING_FEATURE_DETAIL("distinguishing-feature-detail"),
	EMPLOYEE_TYPE("employee-type"),
	ENTRY_POINT("entry-point"),
	EVENT_TYPE("event-type"),
	EXTERNAL_AGENCY("external-agency"),
	INCIDENT_LOCATION("incident-location"),
	INCIDENT_LOCATION_DETAIL("incident-location-detail"),
	INCIDENT_TYPE("incident-type"),
	INJURY_CAUSE("injury-cause"),
	INJURY_TYPE("injury-type"),
	INJURY_TYPE_DETAIL("injury-type-detail"),
	INJURY_TYPE_DETAIL_SPEC("injury-type-detail-spec"),
	SUSPECT_TYPE("suspect-type"),
	WEAPON_INVOLVED("weapon-involved"),
	ACCIDENT_TYPE("accident-type"),
	BODY_PART("body-part"),
	DOCUMENT_CATEGORY("document-category"),
	DOCUMENT_TYPE("document-type"),
	GENDER_TYPE("gender-type"),
	INCIDENT_CATEGORY("incident-category"),
	INJURED_PERSON_TYPE("injured-person-type"),
	LOSS_TYPE("loss-type"),
	POLICY_TYPE("policy-type"),
	VEHICLE_DAMAGE_TYPE("vehicle-damage-type");	
	
	public String value;
	
	private TableMaintenanceTableNames(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}	
}
