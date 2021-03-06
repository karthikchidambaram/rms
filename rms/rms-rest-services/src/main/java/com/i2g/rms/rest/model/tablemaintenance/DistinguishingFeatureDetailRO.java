package com.i2g.rms.rest.model.tablemaintenance;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.CrimeSuspectRO;
import com.i2g.rms.rest.model.InjuredPersonRO;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.WitnessRO;

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
	private Set<SuspectRO> _suspects = new HashSet<SuspectRO>(0);
	private Set<InjuredPersonRO> _injuredPersons = new HashSet<InjuredPersonRO>(0);
	private Set<CrimeSuspectRO> _crimeSuspects = new HashSet<CrimeSuspectRO>(0);
	private Set<WitnessRO> _witnesses = new HashSet<WitnessRO>(0);

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
	
	public Set<SuspectRO> getSuspects() {
		return _suspects;
	}

	public void setSuspects(final Set<SuspectRO> suspects) {
		_suspects = suspects;
	}

	public Set<InjuredPersonRO> getInjuredPersons() {
		return _injuredPersons;
	}

	public void setInjuredPersons(final Set<InjuredPersonRO> injuredPersons) {
		_injuredPersons = injuredPersons;
	}

	public Set<CrimeSuspectRO> getCrimeSuspects() {
		return _crimeSuspects;
	}

	public void setCrimeSuspects(final Set<CrimeSuspectRO> crimeSuspects) {
		_crimeSuspects = crimeSuspects;
	}

	public Set<WitnessRO> getWitnesses() {
		return _witnesses;
	}

	public void setWitnesses(final Set<WitnessRO> witnesses) {
		_witnesses = witnesses;
	}
}
