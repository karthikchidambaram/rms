package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.model.DeleteRO;
import com.i2g.rms.rest.model.wrapper.AddressWrapper;

/**
 * Rest Service Interface for address rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AddressRestService {

	public List<AddressRO> get();

	public AddressRO get(final long addressId);

	public List<AddressRO> getAddressesForUserId(final Long userId);

	public List<AddressRO> getAddressesForUserLoginId(final String userLoginId);

	public List<AddressRO> getAddressesForBuildingId(final Long buildingId);

	public List<AddressRO> getAddressesForInjuredPersonId(final Long injuredPersonId);

	public List<AddressRO> getAddressesForSuspectId(final Long suspectId);

	public List<AddressRO> getAddressesForCrimeSuspectId(final Long crimeSuspectId);

	public List<AddressRO> getAddressesForWitnessId(final Long witnessId);

	public AddressRO create(final AddressRO addressRO);

	public List<AddressRO> createAddresses(final AddressWrapper addressWrapper);

	public AddressRO updateAddress(final AddressRO addressRO);

	public List<AddressRO> updateAddresses(final AddressWrapper addressWrapper);

	public void deleteAddress(final Long addressId);

	public void deleteAddresses(final DeleteRO deleteRO);

}
