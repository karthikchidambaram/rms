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
public class InjuryTypeDetailSpecRO extends AbstractEntityRO {

	private String _id;
	private String _description;
	private String _parentId;
	private InjuryTypeDetailRO _injuryTypeDetail;

	public String getId() {
		return _id;
	}

	public void setId(final String id) {
		_id = id;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(final String description) {
		_description = description;
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

	/**
	 * @return the injuryTypeDetail
	 */
	public InjuryTypeDetailRO getInjuryTypeDetail() {
		return _injuryTypeDetail;
	}

	/**
	 * @param injuryTypeDetail
	 *            the injuryTypeDetail to set
	 */
	public void setInjuryTypeDetail(final InjuryTypeDetailRO injuryTypeDetail) {
		_injuryTypeDetail = injuryTypeDetail;
	}

}
