package com.i2g.rms.domain.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Spring provides org.springframework.web.multipart.MultipartFile which is a
 * representation of an uploaded file received in a multipart request. It
 * provides handy methods like getName(), getContentType(), getBytes(),
 * getInputStream() etc.. which make life bit easier while retrieving
 * information about file being uploaded.
 * 
 * @author Karthikeyan Chidambaram
 *
 */
public class FileBucket {

	private MultipartFile _file;

	public MultipartFile getFile() {
		return _file;
	}

	public void setFile(final MultipartFile file) {
		_file = file;
	}
}
