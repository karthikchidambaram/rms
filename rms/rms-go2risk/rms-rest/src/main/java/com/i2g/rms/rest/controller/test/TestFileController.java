package com.i2g.rms.rest.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.service.test.TestFileRestService;

/**
 * Rest controller for testing file upload.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 */
@RestController
public class TestFileController extends TestAbstractRestController {

	@Autowired
	private TestFileRestService _testFileRestService;

	@RequestMapping(value = RequestMappingConstants.TEST_FILE_UPLOAD, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void saveFiles(@RequestParam("name") String[] names, @RequestParam("description") String[] descriptions, @RequestParam("file") final MultipartFile[] files) {
		_testFileRestService.saveFiles(names, descriptions, files);
	}	
}
