package com.i2g.rms.rest.model.tablemaintenance;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.InjuredPersonRO;

/**
 * REST Object for returning table maintenance details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BodyPartRO extends AbstractEntityRO {
	
	public enum BodyPartFrontOrBackRO {
		
		FRONT("Front"),
		BACK("Back");

		public String value;

		private BodyPartFrontOrBackRO(final String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	private String _id;
	private String _description;
	private Set<InjuredPersonRO> _injuredPersons = new HashSet<InjuredPersonRO>(0);
	private BodyPartFrontOrBackRO _bodyPartFrontOrBack;

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
	 * @return the injuredPersons
	 */
	public Set<InjuredPersonRO> getInjuredPersons() {
		return _injuredPersons;
	}

	/**
	 * @param injuredPersons
	 *            the injuredPersons to set
	 */
	public void setInjuredPersons(final Set<InjuredPersonRO> injuredPersons) {
		_injuredPersons = injuredPersons;
	}

	public BodyPartFrontOrBackRO getBodyPartFrontOrBack() {
		return _bodyPartFrontOrBack;
	}

	public void setBodyPartFrontOrBack(final BodyPartFrontOrBackRO bodyPartFrontOrBack) {
		_bodyPartFrontOrBack = bodyPartFrontOrBack;
	}
}
