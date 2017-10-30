package com.i2g.rms.rest.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.model.CrimeSuspectRO;
import com.i2g.rms.rest.model.wrapper.CrimeSuspectWrapper;
import com.i2g.rms.rest.model.wrapper.DistinguishingFeatureDetailWrapper;

/**
 * Rest Service Interface for crime suspect rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface CrimeSuspectRestService {

	public List<CrimeSuspectRO> get();

	public CrimeSuspectRO get(final Long crimeSuspectId);

	public CrimeSuspectRO createCrimeSuspect(final CrimeSuspectRO crimeSuspectRO);

	public Set<CrimeSuspectRO> createCrimeSuspects(final CrimeSuspectWrapper suspectWrapper);

	public CrimeSuspectRO updateCrimeSuspect(final CrimeSuspectRO crimeSuspectRO);

	public Set<CrimeSuspectRO> updateCrimeSuspects(final CrimeSuspectWrapper suspectWrapper);

	public void removeDistinguishingFeatureDetailsFromCrimeSuspect(final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper);

	/** methods exposed to other services */
	
	public CrimeSuspect constructNewCrimeSuspect(final CrimeSuspectRO crimeSuspectRO);
	
	public CrimeSuspect constructCrimeSuspect(final CrimeSuspectRO crimeSuspectRO);
	
	public CrimeSuspect setOtherFieldsForCrimeSuspect(final CrimeSuspect crimeSuspect, final CrimeSuspectRO crimeSuspectRO);	
		
}
