package com.i2g.rms.service.test;

import java.util.List;

import com.i2g.rms.domain.model.test.TestFile;

/**
 * Base service interface for file upload testing purpose.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TestFileService {

	public List<TestFile> get();

	public TestFile get(final long id);

	public void saveFiles(final List<TestFile> files);

}
