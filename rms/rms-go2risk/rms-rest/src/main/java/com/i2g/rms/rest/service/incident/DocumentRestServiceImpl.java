package com.i2g.rms.rest.service.incident;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.i2g.rms.domain.model.Document;
import com.i2g.rms.domain.model.DocumentView;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.model.DocumentRO;
import com.i2g.rms.rest.model.DocumentViewRO;
import com.i2g.rms.rest.model.StatusFlagRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.rest.service.RestMessage;
import com.i2g.rms.service.DocumentService;
import com.i2g.rms.service.DocumentViewService;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.incident.IncidentService;

/**
 * Rest services for document rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class DocumentRestServiceImpl extends AbstractRestService implements DocumentRestService {

	private final Logger _logger = LoggerFactory.getLogger(DocumentRestServiceImpl.class);

	@Autowired
	private DocumentService _documentService;
	@Autowired
	private DocumentViewService _documentViewService;
	@Autowired
	private IncidentService _incidentService;
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<DocumentViewRO> get() {
		List<DocumentView> documents = _documentViewService.get();
		List<DocumentViewRO> documentROs = documents.isEmpty() ? Collections.emptyList() : _mapperService.map(documents, DocumentViewRO.class); 
		return documentROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public DocumentViewRO get(final long id) {
		if (id > 0) {
			final DocumentView documentView = _documentViewService.get(id);
			validateGenericObject(documentView);
			return _mapperService.map(documentView, DocumentViewRO.class);			
		} else {
			return null;
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<DocumentViewRO> getDocumentsByUniqueIncidentId(final String uniqueIncidentId) {
		//validate unique incident id
		validateUniqueIncidentId(uniqueIncidentId);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId);
		validateObject(incident);
		return _mapperService.map(_documentViewService.getDocumentsByIncidentId(incident.getId()), DocumentViewRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<DocumentRO> getOriginalDocuments() {
		List<Document> documents = _documentService.get();
		List<DocumentRO> documentROs = documents.isEmpty() ? Collections.emptyList() : _mapperService.map(documents, DocumentRO.class); 
		return documentROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public DocumentRO getOriginalDocument(final long id) {
		if (id > 0) {
			final Document document = _documentService.get(id);
			if (document != null) {
				return _mapperService.map(document, DocumentRO.class);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<DocumentRO> getOriginalDocumentsByUniqueIncidentId(final String uniqueIncidentId) {
		//validate unique incident id
		validateUniqueIncidentId(uniqueIncidentId);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId);
		validateObject(incident);
		return _mapperService.map(_documentService.getDocumentsByIncident(incident), DocumentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public DocumentViewRO saveDocument(
				final String uniqueIncidentId,
				final String fileDescription,
				final MultipartFile file
				) {
		//Validate unique incident id
		validateUniqueIncidentId(uniqueIncidentId);
		if (fileDescription == null || fileDescription.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.FILE_UPLOAD_VALIDATION_ERROR, "File description(s) are missing."));
		}
		if (file == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.FILE_UPLOAD_VALIDATION_ERROR, "File(s) to upload are missing."));
		}
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId);
		validateObject(incident);
		//construct the document object and save
		final Document document  = constructNewDocument(incident, file, fileDescription);
		if (document != null) {
			return getDocumentViewRO(_documentService.saveDocument(document));			
		} else {
			return null;
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public List<DocumentViewRO> saveDocuments(
				final String uniqueIncidentId,
				final String[] fileDescriptions,
				final MultipartFile[] files
				) {
		//perform validations
		validateFilesBeforeSave(uniqueIncidentId, fileDescriptions, files);
		// Construct the incident object for update
		Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId.trim());
		validateGenericObject(incident);
		//Prepare documents to save
		final List<Document> documents = new ArrayList<Document>(0);
		
		for (int i = 0; i < files.length; i++) {
			documents.add(constructNewDocument(incident, files[i], fileDescriptions[i]));						
		}
		
		//try to save documents
		if (documents != null && !documents.isEmpty() && (documents.size() > 0)) {
			return getDocumentViewROs(_documentService.saveDocuments(documents));				
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public void deleteDocument(final Long id) {
		if (id == null || id <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.INVALID_KEY_PASSED_FOR_DELETE));
		}
		final Document document = _documentService.get(id);
		validateGenericObject(document);
		_documentService.deleteDocument(document);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public void deleteDocuments(final Long[] ids) {
		if (ids == null || ids.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		Set<Document> documents = new HashSet<Document>(0);
		for (int i = 0; i < ids.length; i++) {
			final Document document = _documentService.get(ids[i]);
			if (document != null) {
				documents.add(document);
			}
		}
		_documentService.deleteDocuments(documents);
	}
	
	@Override
	public void exceptionDuringFileDownload(final String exceptionType) {
		if (exceptionType != null && !exceptionType.trim().isEmpty()) {
			if (exceptionType.equalsIgnoreCase("IOException")) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.IOEXCEPTION_WHILE_FILE_DOWNLOAD));
			} else if (exceptionType.equalsIgnoreCase("UnableToFetchFileForDownload")) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.UNABLE_TO_FETCH_FILE_FOR_DOWNLOAD));
			} else if (exceptionType.equalsIgnoreCase("GenericFetchFailedMessage")) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
			}
		} else {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_DOWNLOAD_ERROR_MESSAGE)); 
		}
	}
	
	private Document constructNewDocument(final Incident incident, final MultipartFile file, final String fileDescription) {
		Document document = new Document.Builder().setStatusFlag(StatusFlag.ACTIVE).setIncident(incident).build();
		if (file != null) {
			try
			{
				String fileName = file.getName();
				String fileContentType = file.getContentType();
				String originalFileName = file.getOriginalFilename();
				long fileSize = file.getSize();
				byte[] bytes = file.getBytes();
				Blob blob = new SerialBlob(bytes);
				
				if (fileName != null && !fileName.trim().isEmpty()) {
					document.setFileName(fileName);
				}
				if (fileDescription != null && !fileDescription.trim().isEmpty()) {
					document.setFileDescription(fileDescription);
				}
				if (fileContentType != null && !fileContentType.trim().isEmpty()) {
					document.setFileContentType(fileContentType);
				}
				if (originalFileName != null && !originalFileName.trim().isEmpty()) {
					document.setOriginalFileName(originalFileName);
				}
				if (fileSize > 0) {
					document.setFileSize(fileSize);
				}
				if (blob != null) {
					document.setFileContent(blob);
				}
			} catch (IOException iOException) {
				_logger.error("IOException while parsing BLOB: " + iOException.toString());
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.ERROR_WHILE_PARSING_FILE_BEFORE_UPLOAD));
			} catch (SerialException serialException) {
				_logger.error("SerialException while parsing BLOB: " + serialException.toString());
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.ERROR_WHILE_PARSING_FILE_BEFORE_UPLOAD));
			} catch (SQLException sQLException) {
				_logger.error("SQLException while parsing BLOB: " + sQLException.toString());
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.ERROR_WHILE_PARSING_FILE_BEFORE_UPLOAD));
			} catch (Exception exception) {
				_logger.error("Exception while parsing BLOB: " + exception.toString());
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.ERROR_WHILE_PARSING_FILE_BEFORE_UPLOAD));
			}
		}
		return document;
	}
	
	private void validateFilesBeforeSave(
			final String uniqueIncidentId,
			final String[] fileDescriptions,
			final MultipartFile[] files
			) {
		
		//Validate Unique Incident Id
		validateUniqueIncidentId(uniqueIncidentId);
		
		//Validate document description and files.
		if (fileDescriptions == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.FILE_UPLOAD_VALIDATION_ERROR, "File description(s) are missing."));
		}		
		if (files == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.FILE_UPLOAD_VALIDATION_ERROR, "File(s) to upload are missing."));
		}
		//validate files and description for length match
		//If the file descriptions doesnt match with no. of files
		if (files.length != fileDescriptions.length) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.FILE_UPLOAD_PARAMETERS_DOES_NOT_MATCH));
		}
	}
	
	private DocumentViewRO getDocumentViewRO(final Document document) {
		if (document != null) {
			DocumentViewRO documentViewRO = new DocumentViewRO();
			if (document.getId() > 0) {
				documentViewRO.setId(document.getId());
			}
			if (document.getIncident().getId() != null && document.getIncident().getId() > 0) {
				documentViewRO.setIncidentId(document.getIncident().getId());
			}
			StatusFlagRO statusFlag = StatusFlagRO.ACTIVE;
			if (document.getStatusFlag() != null && document.getStatusFlag().name().equals("INACTIVE")) {
				statusFlag = StatusFlagRO.INACTIVE;
			}
			documentViewRO.setStatusFlag(statusFlag);
			if (document.getFileName() != null && !document.getFileName().trim().isEmpty()) {
				documentViewRO.setFileName(document.getFileName().trim());
			}
			if (document.getOriginalFileName() != null && !document.getOriginalFileName().trim().isEmpty()) {
				documentViewRO.setOriginalFileName(document.getOriginalFileName().trim());
			}
			if (document.getFileDescription() != null && !document.getFileDescription().trim().isEmpty()) {
				documentViewRO.setFileDescription(document.getFileDescription().trim());
			}
			if (document.getFileContentType() != null && !document.getFileContentType().trim().isEmpty()) {
				documentViewRO.setFileContentType(document.getFileContentType().trim());
			}
			if (document.getFileSize() != null && document.getFileSize() > 0) {
				documentViewRO.setFileSize(document.getFileSize());
			}			
			return documentViewRO;
		} else {
			return null;
		}
	}
	
	public List<DocumentViewRO> getDocumentViewROs(final List<Document> documents) {
		List<DocumentViewRO> documentViewROs = new ArrayList<DocumentViewRO>(0);
		if (documents != null && !documents.isEmpty()) {
			for (Document document : documents) {
				if (document != null) {
					documentViewROs.add(getDocumentViewRO(document));
				}
			}
		}
		return documentViewROs;
	}
}
