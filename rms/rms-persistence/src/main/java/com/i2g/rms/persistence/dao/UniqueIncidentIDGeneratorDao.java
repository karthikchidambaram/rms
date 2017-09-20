package com.i2g.rms.persistence.dao;

/**
 * Back-end DAO for Generating unique incident reference number by using oracle
 * sequences.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface UniqueIncidentIDGeneratorDao {

	public Long getUniqueIncidentID();

}
