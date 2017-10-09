package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * REST Object to carry ids for delete.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteRO {

	private Long[] _ids;

	/**
	 * @return the ids
	 */
	public Long[] getIds() {
		return _ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(final Long[] ids) {
		_ids = ids;
	}
}