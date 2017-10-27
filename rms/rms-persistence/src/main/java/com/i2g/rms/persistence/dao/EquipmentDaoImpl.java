package com.i2g.rms.persistence.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.Equipment;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for EquipmentDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class EquipmentDaoImpl extends AbstractHibernateDao<Long, Equipment> implements EquipmentDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(EquipmentDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link EquipmentDaoImpl}.
	 */
	private EquipmentDaoImpl() {
		super(Equipment.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all Equipment details
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Equipment> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Equipment>) applySearch(criteria).list();
	}

	// Method to return Equipment identified by id.
	@SuppressWarnings("deprecation")
	@Override
	public Equipment get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Equipment ID cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Equipment) criteria.uniqueResult();
	}
	
	@Override
	public Equipment get(final String equipmentId) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("equipmentId", Objects.requireNonNull(equipmentId, "Equipment (Unique Reference) ID cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Equipment) criteria.uniqueResult();
	}

	@Override
	public Equipment createEquipment(final Equipment equipment) {
		validateObject(equipment);
		final Long id = save(equipment);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
	
	@Override
	public Set<Equipment> createEquipments(final Set<Equipment> equipments) {
		validateCollectionObject(equipments);
		Set<Equipment> newEquipments = new HashSet<Equipment>(0);
		for (Equipment equipment : equipments) {
			if (equipment != null) {
				final Long id = save(equipment);
				if (id != null) {
					newEquipments.add(get(id));
				}
			}
		}
		return newEquipments;
	}
	
	@Override
	public Equipment updateEquipment(final Equipment equipment) {
		validateObject(equipment);
		final Long id = save(equipment);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
	
	@Override
	public Set<Equipment> updateEquipments(final Set<Equipment> equipments) {
		validateCollectionObject(equipments);
		Set<Equipment> newEquipments = new HashSet<Equipment>(0);
		for (Equipment equipment : equipments) {
			if (equipment != null) {
				final Long id = save(equipment);
				if (id != null) {
					newEquipments.add(get(id));
				}
			}
		}
		return newEquipments;
	}	
}
