package com.i2g.rms.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * To handle Multiple uploads.
 * 
 * @author Karthikeyan Chidambaram
 *
 */
public class MultiFileBucket {

	List<FileBucket> _files = new ArrayList<FileBucket>(0);

	/**
	 * This class can handle up to 5 file uploads.
	 */
	public MultiFileBucket() {
		_files.add(new FileBucket());
		_files.add(new FileBucket());
		_files.add(new FileBucket());
		_files.add(new FileBucket());
		_files.add(new FileBucket());
	}

	public List<FileBucket> getFiles() {
		return _files;
	}

	public void setFiles(final List<FileBucket> files) {
		_files = files;
	}
}
