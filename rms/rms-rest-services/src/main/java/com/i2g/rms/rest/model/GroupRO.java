package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * REST Object for returning group details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupRO extends AbstractEntityRO {

	private long _id;
	private String _groupName;
	private String _groupDescription;

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getGroupName() {
		return _groupName;
	}

	public void setGroupName(String groupName) {
		_groupName = groupName;
	}

	public String getGroupDescription() {
		return _groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		_groupDescription = groupDescription;
	}
}
