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

import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.Vehicle;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for VehicleDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class VehicleDaoImpl extends AbstractHibernateDao<Long, Vehicle> implements VehicleDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(VehicleDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link VehicleDaoImpl}.
	 */
	private VehicleDaoImpl() {
		super(Vehicle.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all Vehicle details
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Vehicle> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Vehicle>) applySearch(criteria).list();
	}

	// Method to return Vehicle identified by id.
	@SuppressWarnings("deprecation")
	@Override
	public Vehicle get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Vehicle ID cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Vehicle) criteria.uniqueResult();
	}
	
	@Override
	public Vehicle get(final String vehicleRegistrationId) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("vehicleRegistrationId", Objects.requireNonNull(vehicleRegistrationId, "Vehicle Registration ID cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Vehicle) criteria.uniqueResult();
	}

	@Override
	public Vehicle createVehicle(final Vehicle vehicle) {
		validateObject(vehicle);
		final Long id = save(vehicle);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
	
	@Override
	public Set<Vehicle> createVehicles(final Set<Vehicle> vehicles) {
		validateCollectionObject(vehicles);
		Set<Vehicle> newVehicles = new HashSet<Vehicle>(0);
		for (Vehicle vehicle : vehicles) {
			if (vehicle != null) {
				final Long id = save(vehicle);
				if (id != null) {
					newVehicles.add(get(id));
				}
			}
		}
		return newVehicles;
	}
	
	@Override
	public Vehicle updateVehicle(final Vehicle vehicle) {
		validateObject(vehicle);
		final Long id = save(vehicle);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
	
	@Override
	public Set<Vehicle> updateVehicles(final Set<Vehicle> vehicles) {
		validateCollectionObject(vehicles);
		Set<Vehicle> newVehicles = new HashSet<Vehicle>(0);
		for (Vehicle vehicle : vehicles) {
			if (vehicle != null) {
				final Long id = save(vehicle);
				if (id != null) {
					newVehicles.add(get(id));
				}
			}
		}
		return newVehicles;
	}	
}
