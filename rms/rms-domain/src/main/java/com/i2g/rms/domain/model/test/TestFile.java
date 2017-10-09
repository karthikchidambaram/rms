package com.i2g.rms.domain.model.test;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * Entity object for table RMS_TEST_FILE_DB
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Entity
@Table(name = "RMS_TEST_FILE_DB")
public class TestFile extends AbstractDataModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key ID for entity. */
	private Long _id;
	private String _fileName;
	private String _fileDescription;
	private Blob _fileContent;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	public TestFile() {
	}

	/**
	 * Returns the primary key ID for the record.
	 * 
	 * @return primary key ID
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_test_file_db_id_seq")
	@SequenceGenerator(name = "rms_test_file_db_id_seq", sequenceName = "RMS_TEST_FILE_DB_ID_SEQ", allocationSize = 1)
	@Override
	public Long getId() {
		return _id;
	}

	/**
	 * Sets the primary surrogate key ID.
	 * 
	 * @param id
	 */
	protected void setId(final Long id) {
		_id = id;
	}

	@Column(name = "FILE_NAME", length = 128)
	public String getFileName() {
		return _fileName;
	}

	public void setFileName(final String fileName) {
		_fileName = fileName;
	}

	@Column(name = "FILE_DESC", length = 256)
	public String getFileDescription() {
		return _fileDescription;
	}

	public void setFileDescription(final String fileDescription) {
		_fileDescription = fileDescription;
	}

	@Lob
	@Column(name = "FILE_CONTENT")
	public Blob getFileContent() {
		return _fileContent;
	}

	public void setFileContent(final Blob fileContent) {
		_fileContent = fileContent;
	}
}
