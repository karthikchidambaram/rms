package com.i2g.rms.persistence.dao.test;

import java.util.List;

import com.i2g.rms.domain.model.test.TestFile;

/**
 * Data Access Object for Testing File upload purposes.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TestFileDao {	
	
	public List<TestFile> get();
	
	public TestFile get(final long id);
	
	public void saveFiles(final List<TestFile> files);
	
}
