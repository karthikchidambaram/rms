package com.i2g.rms.rest.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.model.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.SuspectWrapper;

/**
 * Rest Service Interface for role rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface SuspectRestService {
	
	public List<SuspectRO> get();
	
	public SuspectRO get(final long id);
	
	public SuspectRO createSuspect(final SuspectRO suspectRO);
	
	public List<SuspectRO> createSuspects(final SuspectWrapper suspectWrapper);	
	
	public SuspectRO udpateSuspect(final SuspectRO suspectRO);
	
	public List<SuspectRO> udpateSuspects(final SuspectWrapper suspectWrapper);
	
	public void removeDistinguishingFeatureDetailsFromSuspect(final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper);
	
	/** methods exposed to other services */
	public Suspect constructNewSuspect(final SuspectRO suspectRO);
	public Suspect constructSuspectForUpdate(final SuspectRO suspectRO);
	public Set<Address> updateAddresses(final Set<AddressRO> addressROs);
}
