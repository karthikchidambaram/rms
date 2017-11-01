package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.domain.model.Investigation;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.model.InvestigationRO;
import com.i2g.rms.rest.model.wrapper.InvestigationWrapper;

/**
 * Rest Service Interface for investigation rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InvestigationRestService {

	public List<InvestigationRO> get();
	
	public List<InvestigationRO> get(final String investigatorLoginId);

	public InvestigationRO get(final Long investigationId);
	
	public InvestigationRO getInvestigationByIncidentId(final Long incidentId);
	
	public InvestigationRO getInvestigationByUniqueIncidentId(final String uniqueIncidentId);

	public InvestigationRO createInvestigation(final InvestigationRO investigationRO);

	public InvestigationRO updateInvestigation(final InvestigationRO investigationRO);
	
	public InvestigationRO addOrUpdateInvestigation(final InvestigationRO investigationRO);

	public void deleteInvestigation(final Long investigationId);

	public void deleteInvestigations(final InvestigationWrapper investigationWrapper);

	public Investigation constructNewInvestigation(final Incident incident, final InvestigationRO investigationRO);

	public Investigation constructInvestigation(final InvestigationRO investigationRO);

	public Investigation setOtherFieldsForInvestigation(final Investigation investigation, final InvestigationRO investigationRO);

}
