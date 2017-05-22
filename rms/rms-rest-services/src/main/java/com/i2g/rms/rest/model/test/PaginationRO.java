package com.i2g.rms.rest.model.test;

import com.i2g.rms.rest.model.AbstractEntityRO;

/**
 * PaginationRO Object for RMS Application - Presentation Layer.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public class PaginationRO extends AbstractEntityRO {
	
	private long _id;
	private String _type;
	private String _description;
	
	public long getId() {
		return _id;
	}
	
	public void setId(long id) {
		_id = id;
	}
	
	public String getType() {
		return _type;
	}
	
	public void setType(String type) {
		_type = type;
	}
	
	public String getDescription() {
		return _description;
	}
	
	public void setDescription(String description) {
		_description = description;
	}		
}
