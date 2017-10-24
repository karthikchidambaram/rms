package com.i2g.rms.rest.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.rest.model.InjuredPersonRO;
import com.i2g.rms.rest.model.wrapper.BodyPartWrapper;
import com.i2g.rms.rest.model.wrapper.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.model.wrapper.InjuredPersonWrapper;

/**
 * Rest Service Interface for injured person rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuredPersonRestService {

	public List<InjuredPersonRO> get();

	public InjuredPersonRO get(final Long injuredPersonId);

	public InjuredPersonRO createInjuredPerson(final InjuredPersonRO injuredPersonRO);

	public Set<InjuredPersonRO> createInjuredPersons(final InjuredPersonWrapper injuredPersonWrapper);

	public InjuredPersonRO updateInjuredPerson(final InjuredPersonRO injuredPersonRO);

	public Set<InjuredPersonRO> updateInjuredPersons(final InjuredPersonWrapper injuredPersonWrapper);

	public void removeDistinguishingFeatureDetailsFromInjuredPerson(final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper);

	public void removeBodyPartsFromInjuredPerson(final BodyPartWrapper bodyPartWrapper);

	public InjuredPerson constructNewInjuredPerson(final InjuredPersonRO injuredPersonRO);

	public InjuredPerson constructInjuredPerson(final InjuredPersonRO injuredPersonRO);

	public InjuredPerson setOtherFieldsForInjuredPerson(final InjuredPerson injuredPerson, final InjuredPersonRO injuredPersonRO);

}
