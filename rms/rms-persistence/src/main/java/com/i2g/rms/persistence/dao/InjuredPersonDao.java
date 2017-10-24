package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.tablemaintenance.BodyPart;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;

/**
 * Back-end DAO for Injured Person related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuredPersonDao {	
	
	public List<InjuredPerson> get();
	
	public InjuredPerson get(final long id);
	
	public InjuredPerson createInjuredPerson(final InjuredPerson injuredPerson);
	
	public Set<InjuredPerson> createInjuredPersons(final Set<InjuredPerson> injuredPersons);	
	
	public InjuredPerson updateInjuredPerson(final InjuredPerson injuredPerson);
	
	public Set<InjuredPerson> updateInjuredPersons(final Set<InjuredPerson> injuredPersons);
	
	public void removeDistinguishingFeatureDetailsFromInjuredPerson(final InjuredPerson injuredPerson, final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails);
	
	public void removeBodyPartsFromInjuredPerson(final InjuredPerson injuredPerson, final Set<BodyPart> bodyParts);
	
}
