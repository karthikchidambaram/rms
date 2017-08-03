package com.i2g.rms.util.test.model.tablemaintenance;

import com.i2g.rms.util.test.model.AbstractEntityRO;

public class EntryPointRO extends AbstractEntityRO {

	private String _id;
	private String _description;

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		_id = id;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}
}
