package com.i2g.rms.rest.model.tablemaintenance;

import java.util.HashSet;
import java.util.Set;

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
public class DistinguishingFeaturesRO extends AbstractEntityRO {

	private String _id;
	private String _description;
	private Set<DistinguishingFeaturesDetailRO> _distinguishingFeaturesDetail = new HashSet<DistinguishingFeaturesDetailRO>(0);
	
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
	
	public Set<DistinguishingFeaturesDetailRO> getDistinguishingFeaturesDetail() {
		return _distinguishingFeaturesDetail;
	}

	public void setDistinguishingFeaturesDetail(Set<DistinguishingFeaturesDetailRO> distinguishingFeaturesDetail) {
		_distinguishingFeaturesDetail = distinguishingFeaturesDetail;
	}
}
