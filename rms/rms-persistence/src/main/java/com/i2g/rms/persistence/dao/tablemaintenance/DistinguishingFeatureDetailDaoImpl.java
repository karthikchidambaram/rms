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

import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeature;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
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
public class DistinguishingFeatureDetailDaoImpl extends AbstractHibernateDao<String, DistinguishingFeatureDetail>
		implements DistinguishingFeatureDetailDao {

	private final Logger _logger = LoggerFactory.getLogger(DistinguishingFeatureDetailDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link DistinguishingFeatureDetailDaoImpl}.
	 */
	private DistinguishingFeatureDetailDaoImpl() {
		super(DistinguishingFeatureDetail.class);
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
	public List<DistinguishingFeatureDetail> get() {
		return (List<DistinguishingFeatureDetail>) applySearch(
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
	public DistinguishingFeatureDetail getByCode(final String code) {
		// Validate input parameter(s) if any..
		validateCode(code);
		return (DistinguishingFeatureDetail) applySearch(getSession().createCriteria(_modelType)
				.add(Restrictions.eq("id", Objects.requireNonNull(code, "Code cannot be null or empty."))))
						.uniqueResult();
	}

	/**
	 * Create a record in the table maintenance object.
	 * 
	 * @param code
	 * @param description
	 * 
	 * @return a table maintenance object.
	 */
	@Override
	public DistinguishingFeatureDetail create(final String code, final String description,
			final DistinguishingFeature distinguishingFeature) {
		// Validate input parameter(s) if any..
		validateCode(code);
		validateDescription(description);
		validateObject(distinguishingFeature);
		// Create the new object (record)
		DistinguishingFeatureDetail object = new DistinguishingFeatureDetail(code, description, distinguishingFeature);
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
	public DistinguishingFeatureDetail update(final String code, final String description) {
		// Validate input parameter(s) if any..
		validateCode(code);
		validateDescription(description);
		// Check if the record exists before issuing the update.
		DistinguishingFeatureDetail object = getByCode(code);
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
		DistinguishingFeatureDetail object = getByCode(code);
		// Check if the object exists in database before issuing the delete
		validateObject(object);
		// Issue delete
		super.delete(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DistinguishingFeatureDetail> get(final DistinguishingFeature distinguishingFeature) {
		// Check if the object is valid and not null or empty
		validateObject(distinguishingFeature);
		return (List<DistinguishingFeatureDetail>) applySearch(
				getSession().createCriteria(_modelType)
						.addOrder(Order.asc("description").ignoreCase())
						.add(Restrictions.eq("distinguishingFeature", Objects
						.requireNonNull(distinguishingFeature, "Distinguishing feature object cannot be null."))))
								.list();
	}
}
