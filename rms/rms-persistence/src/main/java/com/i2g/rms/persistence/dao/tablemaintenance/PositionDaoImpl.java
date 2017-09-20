package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.tablemaintenance.Department;
import com.i2g.rms.domain.model.tablemaintenance.Organization;
import com.i2g.rms.domain.model.tablemaintenance.Position;
import com.i2g.rms.domain.model.tablemaintenance.PositionLevel;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for Dao interfaces which belongs to table maintenance
 * functionalities.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class PositionDaoImpl extends AbstractHibernateDao<String, Position> implements PositionDao {

	private final Logger _logger = LoggerFactory.getLogger(PositionDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link PositionDaoImpl}.
	 */
	private PositionDaoImpl() {
		super(Position.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	/**
	 * Method which returns all available records. A typical table maintenance
	 * record would be a pair of code and description.
	 * 
	 * @return a list containing code and description pairs.
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Position> get() {
		return (List<Position>) applySearch(
				getSession().createCriteria(_modelType).addOrder(Order.asc("description").ignoreCase())).list();
	}

	/**
	 * Method which will return table maintenance object for the given
	 * {@code code}. A typical table maintenance object will be a code and
	 * description pair.
	 * 
	 * @return entryPoint
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Position getByCode(final String code) {
		// Validate input parameter(s) if any..
		validateCode(code);
		return (Position) applySearch(getSession().createCriteria(_modelType)
				.add(Restrictions.eq("id", Objects.requireNonNull(code, "Code cannot be null or empty."))))
						.uniqueResult();
	}

	/**
	 * Create a record in the table maintenance object.
	 * 
	 * @param code
	 * @param description
	 * @param positionLevel
	 * @param organization
	 * 
	 * @return a table maintenance object.
	 */
	@Override
	public Position create(final String code, final String description, final PositionLevel positionLevel,
			final Organization organization) {
		// Validate input parameter(s) if any..
		validateCode(code);
		validateDescription(description);
		validateObject(positionLevel);
		validateObject(organization);
		// Create the new object (record)
		Position object = new Position(code, description, positionLevel, organization);
		// Issue save
		save(object);
		return object;
	}

	/**
	 * Create a record in the table maintenance object.
	 * 
	 * @param code
	 * @param description
	 * @param organization
	 * 
	 * @return a table maintenance object.
	 */
	@Override
	public Position create(final String code, final String description, final Organization organization) {
		// Validate input parameter(s) if any..
		validateCode(code);
		validateDescription(description);
		validateObject(organization);
		// Create the new object (record)
		Position object = new Position(code, description, organization);
		// Issue save
		save(object);
		return object;
	}

	/**
	 * Create a record in the table maintenance object.
	 * 
	 * @param code
	 * @param description
	 * @param positionLevel
	 * @param department
	 * 
	 * @return a table maintenance object.
	 */
	@Override
	public Position create(final String code, final String description, final PositionLevel positionLevel,
			final Department department) {
		// Validate input parameter(s) if any..
		validateCode(code);
		validateDescription(description);
		validateObject(positionLevel);
		validateObject(department);
		// Create the new object (record)
		Position object = new Position(code, description, positionLevel, department);
		// Issue save
		save(object);
		return object;
	}

	/**
	 * Create a record in the table maintenance object.
	 * 
	 * @param code
	 * @param description
	 * @param department
	 * 
	 * @return a table maintenance object.
	 */
	@Override
	public Position create(final String code, final String description, final Department department) {
		// Validate input parameter(s) if any..
		validateCode(code);
		validateDescription(description);
		validateObject(department);
		// Create the new object (record)
		Position object = new Position(code, description, department);
		// Issue save
		save(object);
		return object;
	}

	/**
	 * Update a record in the table maintenance object. Only the description can
	 * be updated. The code cannot be updated because it is the primary key. If
	 * the code needs to be updated then it is advised to delete the record and
	 * re-create it with the updated code. This is to ensure the uniqueness of
	 * the code.
	 * 
	 * @param code
	 * @param description
	 * 
	 * @return a table maintenance object.
	 */
	@Override
	public Position update(final String code, final String description) {
		// Validate input parameter(s) if any..
		validateCode(code);
		validateDescription(description);
		// Check if the record exists before issuing the update.
		Position object = getByCode(code);
		validateObject(object);
		// Set the new value(s).
		object.setDescription(description);
		// Issue update
		update(object);
		return object;
	}

	/**
	 * Delete a record in the table maintenance by the given {@code code}.
	 * 
	 * @param code
	 */
	@Override
	public void delete(final String code) {
		// Validate input parameter(s) if any..
		validateCode(code);
		Position object = getByCode(code);
		// Check if the object exists in database before issuing the delete
		validateObject(object);
		// Issue delete
		super.delete(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> get(Organization organization) {
		// Check if the object is valid and not null or empty
		validateObject(organization);
		return (List<Position>) applySearch(getSession().createCriteria(_modelType).add(Restrictions.eq("organization",
				Objects.requireNonNull(organization, "Organization object cannot be null.")))).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> get(Department department) {
		// Check if the object is valid and not null or empty
		validateObject(department);
		return (List<Position>) applySearch(getSession().createCriteria(_modelType).add(
				Restrictions.eq("department", Objects.requireNonNull(department, "Department object cannot be null."))))
						.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> get(PositionLevel positionLevel) {
		// Check if the object is valid and not null or empty
		validateObject(positionLevel);
		return (List<Position>) applySearch(
				getSession().createCriteria(_modelType)
						.add(Restrictions.eq("positionLevel",
								Objects.requireNonNull(positionLevel, "Position Level object cannot be null."))))
										.list();
	}
}
