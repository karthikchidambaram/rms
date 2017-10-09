package com.i2g.rms.rest.service.test;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.i2g.rms.domain.model.test.TestFile;
import com.i2g.rms.service.test.TestFileService;

/**
 * Rest services for file upload testing.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TestFileRestServiceImpl extends TestAbstractRestService implements TestFileRestService {

	private final Logger _logger = LoggerFactory.getLogger(TestFileRestServiceImpl.class);

	@Autowired
	private TestFileService _fileService;

	@Override
	@Transactional
	public void saveFiles(final String[] names, final String[] descriptions, final MultipartFile[] files) {
		
		/*System.out.println("File Name: " + file.getName());
		System.out.println("File Size: " + file.getSize());
		System.out.println("ContentType: " + file.getContentType());
		System.out.println("Original File Name: " + file.getOriginalFilename());*/
		
		if (names == null || names.length <= 0) {
			System.out.println("Mandatory Information Missing - names[] is null or empty.");
		}
		
		if (descriptions == null || descriptions.length <= 0) {
			System.out.println("Mandatory Information Missing - descriptions[] is null or empty.");
		}
		
		if (files == null || files.length <= 0) {
			System.out.println("Mandatory Information Missing - multipart files[] is null or empty.");
		}
		
		if (files.length != names.length) {
			System.out.println("Mandatory Information Missing: files and names are not matching.");
		}
		
		if (files.length != descriptions.length) {
			System.out.println("Mandatory Information Missing: files and descriptions are not matching.");
		}
		
		List<TestFile> testFiles = new ArrayList<TestFile>(0);
		
		for (int i = 0; i < files.length; i++) {
			String name = names[i];
			String description = descriptions[i];
			MultipartFile file = files[i];			
			
			try {
				TestFile testFile = new TestFile();
				testFile.setFileName(name);
				testFile.setFileDescription(description);
				
				byte[] bytes = file.getBytes();
				Blob blob = new SerialBlob(bytes);
				if (blob != null) {
					testFile.setFileContent(blob);
				}
				testFiles.add(testFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SerialException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		if (testFiles != null && !testFiles.isEmpty()) {
			_fileService.saveFiles(testFiles);
		}
		System.out.println("File upload(s) completed!");
	}
}
