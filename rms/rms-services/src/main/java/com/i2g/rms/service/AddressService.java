package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Witness;

/**
 * Service interface for all address related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AddressService {
	
	public List<Address> get();
	
	public Address get(final long id);
	
	public Address create(final Address address);
	
	public Address updateAddress(final Address address);
	
	public List<Address> updateAddresses(final List<Address> addresses);
	
	public List<Address> get(final User user);
	public List<Address> get(final Building building);
	public List<Address> get(final Witness witness);
	public List<Address> get(final InjuredPerson injuredPerson);
	public List<Address> get(final Suspect suspect);
	public List<Address> get(final CrimeSuspect crimeSuspect);
	
}
