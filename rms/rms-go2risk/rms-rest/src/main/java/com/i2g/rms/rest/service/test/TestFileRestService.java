package com.i2g.rms.rest.service.test;

import org.springframework.web.multipart.MultipartFile;

/**
 * Rest Service Interface for testing file upload.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TestFileRestService {

	public void saveFiles(final String[] names, final String[] descriptions, final MultipartFile[] files);

}
