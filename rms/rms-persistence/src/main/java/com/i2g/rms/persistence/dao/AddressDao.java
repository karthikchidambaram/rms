package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Witness;

/**
 * Back-end DAO for address related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AddressDao {
	
	public List<Address> get();
	
	public Address get(final long id);
	
	public List<Address> get(final User user);
	public List<Address> get(final Building building);
	public List<Address> get(final Witness witness);
	public List<Address> get(final InjuredPerson injuredPerson);
	public List<Address> get(final Suspect suspect);
	public List<Address> get(final CrimeSuspect crimeSuspect);
	
	public Address createAddress(final Address address);
	
	public List<Address> createAddresses(final List<Address> addresses);
	
	public Address updateAddress(final Address address);
	
	public List<Address> updateAddresses(final List<Address> addresses);
	
	public void deleteAddress(final Address address);
	
	public void deleteAddresses(final List<Address> addresses);
}
