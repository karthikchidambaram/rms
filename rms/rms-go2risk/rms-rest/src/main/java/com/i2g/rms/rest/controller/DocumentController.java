package com.i2g.rms.rest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i2g.rms.domain.model.DocumentView;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.DeleteRO;
import com.i2g.rms.rest.model.DocumentRO;
import com.i2g.rms.rest.model.DocumentViewRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.incident.DocumentRestService;

/**
 * Rest controller for document read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class DocumentController extends AbstractRestController {
	
	@Autowired
	private DocumentRestService _documentRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_DOCUMENTS, method = RequestMethod.GET)
	@Searchable(sourceType = DocumentViewRO.class, value = DocumentView.class)
	public List<DocumentViewRO> get() {
		return _documentRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_DOCUMENT_BY_ID, method = RequestMethod.GET)
	public DocumentViewRO get(@PathVariable final long documentId) {
		return _documentRestService.get(documentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_DOCUMENTS_BY_INCIDENT_ID, method = RequestMethod.GET)
	public List<DocumentViewRO> getDocumentsByUniqueIncidentId(@PathVariable final String uniqueIncidentId) {
		return _documentRestService.getDocumentsByUniqueIncidentId(uniqueIncidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.SAVE_DOCUMENTS, method = RequestMethod.POST)
	public List<DocumentViewRO> saveDocuments(
			@RequestParam("uniqueIncidentId") final String uniqueIncidentId,
			@RequestParam("fileDescription") final String[] fileDescriptions,
			@RequestParam("file") final MultipartFile[] files			
			) {
		return _documentRestService.saveDocuments(uniqueIncidentId, fileDescriptions, files);
	}
	
	@RequestMapping(value = RequestMappingConstants.SAVE_DOCUMENT, method = RequestMethod.POST)
	public DocumentViewRO saveDocument(
			@RequestParam("uniqueIncidentId") final String uniqueIncidentId,
			@RequestParam("fileDescription") final String fileDescription,
			@RequestParam("file") final MultipartFile file
			) {
		return _documentRestService.saveDocument(uniqueIncidentId, fileDescription, file);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_DOCUMENT, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDocument(final @PathVariable Long id) {
		_documentRestService.deleteDocument(id);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_DOCUMENTS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDocuments(final @Valid @RequestBody DeleteRO deleteRO) {
		_documentRestService.deleteDocuments(deleteRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.DOWNLOAD_DOCUMENT, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void downloadDocument(final @PathVariable Long id, HttpServletResponse response) {
		final DocumentRO documentRO = _documentRestService.getOriginalDocument(id);
		if (documentRO != null) {
			try {
				response.setContentType(documentRO.getFileContentType());
		        response.setContentLength(documentRO.getFileContent().length);
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + documentRO.getOriginalFileName() +"\"");
		        FileCopyUtils.copy(documentRO.getFileContent(), response.getOutputStream());
			} catch (IOException iOException) {
				_documentRestService.exceptionDuringFileDownload("IOException");
			}
		} else {
			_documentRestService.exceptionDuringFileDownload("UnableToFetchFileForDownload");
		}
	}
}
