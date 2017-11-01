package com.i2g.rms.rest.model.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.incident.BaseIncidentDetailRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentWrapper extends BaseIncidentDetailRO {
	
}
