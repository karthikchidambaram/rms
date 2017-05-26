package com.i2g.rms.util.tablemaintenance;

public enum TableMaintenanceOperations {

	// Possible operations that could be performed on the table maintenance
	// tables.
	GET("get"), 
	GET_BY_CODE("getByCode"), 
	CREATE("create"), 
	UPDATE("update"), 
	DELETE("delete");

	public String value;

	private TableMaintenanceOperations(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
