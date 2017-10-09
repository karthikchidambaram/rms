package com.i2g.rms.service.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.test.TestFile;
import com.i2g.rms.persistence.dao.test.TestFileDao;

/**
 * Back-end test service layer which will work with domain objects.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TestFileServiceImpl extends TestAbstractService implements TestFileService {
	
	private final Logger _logger = LoggerFactory.getLogger(TestFileServiceImpl.class);

	@Autowired
	private TestFileDao _testFileDao;
	
	private TestFileServiceImpl() {}

	@Override
	public List<TestFile> get() {
		return _testFileDao.get();
	}

	@Override
	public TestFile get(long id) {
		return _testFileDao.get(id);
	}

	@Override
	public void saveFiles(final List<TestFile> files) {
		_testFileDao.saveFiles(files);
	}
}
