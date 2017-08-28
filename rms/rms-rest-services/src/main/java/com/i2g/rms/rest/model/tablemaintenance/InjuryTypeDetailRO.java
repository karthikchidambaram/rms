package com.i2g.rms.rest.model.tablemaintenance;

import java.util.HashSet;
import java.util.Set;

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
public class InjuryTypeDetailRO extends AbstractEntityRO {

	private String _id;
	private String _description;
	private InjuryTypeRO _injuryType;
	private Set<InjuryTypeDetailSpecRO> _injuryTypeDetailSpecs = new HashSet<InjuryTypeDetailSpecRO>(0);
	private String _parentId;

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

	public Set<InjuryTypeDetailSpecRO> getInjuryTypeDetailSpecs() {
		return _injuryTypeDetailSpecs;
	}

	public void setInjuryTypeDetailSpecs(final Set<InjuryTypeDetailSpecRO> injuryTypeDetailSpecs) {
		_injuryTypeDetailSpecs = injuryTypeDetailSpecs;
	}

	/**
	 * @return the injuryType
	 */
	public InjuryTypeRO getInjuryType() {
		return _injuryType;
	}

	/**
	 * @param injuryType
	 *            the injuryType to set
	 */
	public void setInjuryType(final InjuryTypeRO injuryType) {
		_injuryType = injuryType;
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
