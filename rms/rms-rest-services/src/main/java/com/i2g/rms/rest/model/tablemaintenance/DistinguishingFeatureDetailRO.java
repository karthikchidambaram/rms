package com.i2g.rms.rest.model.tablemaintenance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;

/**
 * REST Object for returning table maintenance details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistinguishingFeatureDetailRO extends AbstractEntityRO {

	private String _id;
	private String _description;
	private String _parentId;
	private DistinguishingFeatureRO _distinguishingFeature;

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
	 * @return the distinguishingFeature
	 */
	public DistinguishingFeatureRO getDistinguishingFeature() {
		return _distinguishingFeature;
	}

	/**
	 * @param distinguishingFeature
	 *            the distinguishingFeature to set
	 */
	public void setDistinguishingFeature(final DistinguishingFeatureRO distinguishingFeature) {
		_distinguishingFeature = distinguishingFeature;
	}

}
