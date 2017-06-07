package com.i2g.rms.util.tablemaintenance;

public enum TableMaintenanceTableNames {
	
	//All table maintenance table names are declared below:
	ACCIDENT_LOCATION("accident-location"),
	ACCIDENT_LOCATION_DETAILS("accident-location-details"),
	ASSET_CATEGORY("asset-category"),
	CLAIM_REQUEST_REGISTRATION_TYPE("claim-request-registration-type"),
	CLAIM_STATUS("claim-status"),
	CLAIM_TYPE("claim-type"),
	DISTINGUISHING_FEATURES("distinguishing-features"),
	DISTINGUISHING_FEATURES_DETAIL("distinguishing-features-detail"),
	EMPLOYEE_TYPE("employee-type"),
	ENTRY_POINT("entry-point"),
	EVENT_TYPE("event-type"),
	EXTERNAL_AGENCY("external-agency"),
	INCIDENT_LOCATION("incident-location"),
	INCIDENT_LOCATION_DETAILS("incident-location-details"),
	INCIDENT_TYPE("incident-type"),
	INJURY_CAUSE("injury-cause"),
	INJURY_TYPE("injury-type"),
	INJURY_TYPE_DETAILS("injury-type-details"),
	INJURY_TYPE_DETAILS_SPEC("injury-type-details-spec"),
	SUSPECT_TYPE("suspect-type"),
	WEAPON_INVOLVED("weapon-involved");
	
	public String value;
	
	private TableMaintenanceTableNames(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}	
}
