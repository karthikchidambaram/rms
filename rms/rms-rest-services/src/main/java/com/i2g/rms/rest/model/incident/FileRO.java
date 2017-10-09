package com.i2g.rms.rest.model.incident;

import org.springframework.web.multipart.MultipartFile;

import com.i2g.rms.rest.model.tablemaintenance.DocumentCategoryRO;
import com.i2g.rms.rest.model.tablemaintenance.DocumentTypeRO;

public class FileRO {
	
	private MultipartFile file;
	private DocumentTypeRO documentType;
	private String documentName;
	private DocumentCategoryRO documentCategory;
	private String documentDescription;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(final MultipartFile file) {
		this.file = file;
	}

	public DocumentTypeRO getDocumentType() {
		return documentType;
	}

	public void setDocumentType(final DocumentTypeRO documentType) {
		this.documentType = documentType;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(final String documentName) {
		this.documentName = documentName;
	}

	public DocumentCategoryRO getDocumentCategory() {
		return documentCategory;
	}

	public void setDocumentCategory(final DocumentCategoryRO documentCategory) {
		this.documentCategory = documentCategory;
	}

	public String getDocumentDescription() {
		return documentDescription;
	}

	public void setDocumentDescription(final String documentDescription) {
		this.documentDescription = documentDescription;
	}
}
