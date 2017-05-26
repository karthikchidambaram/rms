package com.i2g.rms.util.tablemaintenance;

public enum TableMaintenanceTableNames {
	
	//All table maintenance table names are declared below:
	ENTRY_POINT("entry-point");
	
	public String value;
	
	private TableMaintenanceTableNames(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
