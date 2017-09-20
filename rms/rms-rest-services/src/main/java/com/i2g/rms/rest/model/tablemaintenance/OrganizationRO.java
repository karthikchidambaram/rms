package com.i2g.rms.rest.model.tablemaintenance;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.OfficeAddressRO;

/**
 * REST Object for returning table maintenance details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationRO extends AbstractEntityRO {

	private String _id;
	private String _description;
	private Set<OfficeAddressRO> _officeAddresses = new HashSet<OfficeAddressRO>(0);

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

	public Set<OfficeAddressRO> getOfficeAddresses() {
		return _officeAddresses;
	}

	public void setOfficeAddresses(final Set<OfficeAddressRO> officeAddresses) {
		_officeAddresses = officeAddresses;
	}
}
