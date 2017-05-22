package com.i2g.rms.service.support;

/**
 * Interface for services and containers which provide the ability to reset the
 * internal state, which may include clearing cached data, refreshing data from
 * a persistent store, etc as determined by the implementation.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public interface Resetable {
	/**
	 * Resets the internal state of the instance.
	 */
	void reset();
}
