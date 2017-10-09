package com.i2g.rms.rest.service.incident;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.i2g.rms.rest.model.DocumentRO;
import com.i2g.rms.rest.model.DocumentViewRO;

/**
 * Rest Service for document rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DocumentRestService {
	
	public List<DocumentViewRO> get();
	public DocumentViewRO get(final long id);
	public List<DocumentViewRO> getDocumentsByUniqueIncidentId(final String uniqueIncidentId);
	
	public List<DocumentRO> getOriginalDocuments();
	public DocumentRO getOriginalDocument(final long id);
	public List<DocumentRO> getOriginalDocumentsByUniqueIncidentId(final String uniqueIncidentId);

	public List<DocumentViewRO> saveDocuments(
				final String uniqueIncidentId,
				final String[] fileDescriptions,
				final MultipartFile[] files 
				);
	
	public DocumentViewRO saveDocument(
			final String uniqueIncidentId,
			final String fileDescription,
			final MultipartFile file 
			);

	public void deleteDocumentById(final long id);
	
	public void deleteDocumentByIds(final Long[] ids);
	
	public void exceptionDuringFileDownload(final String exceptionType);

}
