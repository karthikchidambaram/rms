package com.i2g.rms.rest.model.tablemaintenance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i2g.rms.rest.model.AbstractEntityRO;

/**
 * REST Object for returning table maintenance details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccidentLocationDetailRO extends AbstractEntityRO {

	private String _id;
	private String _description;
	private AccidentLocationRO _accidentLocation;
	private String _parentId;

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

	/**
	 * @return the accidentLocation
	 */
	public AccidentLocationRO getAccidentLocation() {
		return _accidentLocation;
	}

	/**
	 * @param accidentLocation
	 *            the accidentLocation to set
	 */
	public void setAccidentLocation(final AccidentLocationRO accidentLocation) {
		_accidentLocation = accidentLocation;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return _parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(final String parentId) {
		_parentId = parentId;
	}
}
