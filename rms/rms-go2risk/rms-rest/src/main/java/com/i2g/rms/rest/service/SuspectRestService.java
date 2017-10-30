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
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.wrapper.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.model.wrapper.SuspectWrapper;

/**
 * Rest Service Interface for suspect rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface SuspectRestService {

	public List<SuspectRO> get();

	public SuspectRO get(final Long suspectId);

	public SuspectRO createSuspect(final SuspectRO suspectRO);

	public List<SuspectRO> createSuspects(final SuspectWrapper suspectWrapper);

	public SuspectRO updateSuspect(final SuspectRO suspectRO);

	public List<SuspectRO> updateSuspects(final SuspectWrapper suspectWrapper);

	public void removeDistinguishingFeatureDetailsFromSuspect(final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper);

	/** methods exposed to other services */
	
	public Suspect constructNewSuspect(final SuspectRO suspectRO);
	
	public Suspect constructSuspect(final SuspectRO suspectRO);
	
	public Suspect setOtherFieldsForSuspect(final Suspect suspect, final SuspectRO suspectRO);
	
	public Set<Address> createOrUpdateAddresses(final Set<AddressRO> addressROs,
												final User user, 
												final Suspect suspect,
												final InjuredPerson injuredPerson, 
												final Witness witness, 
												final CrimeSuspect crimeSuspect,
												final Building building);
	
	public Address getAddress(final AddressRO addressRO,
							final User user, 
							final Suspect suspect,
							final InjuredPerson injuredPerson, 
							final Witness witness, 
							final CrimeSuspect crimeSuspect,
							final Building building);	
	
	public Address setOtherFieldsForAddress(final Address address, final AddressRO addressRO);
	
}
