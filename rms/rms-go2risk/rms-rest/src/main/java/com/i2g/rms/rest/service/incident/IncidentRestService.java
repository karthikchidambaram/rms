package com.i2g.rms.rest.service.incident;

import java.util.List;

import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.incident.IncidentDetailRO;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.incident.LogIncidentRO;

/**
 * Rest Service for password history rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface IncidentRestService {
	
	public List<IncidentRO> get();
	public IncidentRO getIncidentByUniqueIncidentId(final String uniqueIncidentId);
	public UserRO addIncident();
	public IncidentRO logIncident(final LogIncidentRO logIncidentRO);
	public IncidentRO addIncidentDetail(final IncidentDetailRO incidentDetailRO);
}
