package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Implementation class for Dao interfaces which belongs to table maintenance
 * functionalities.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class TableMaintenanceDaoImpl extends AbstractTableMaintenance implements TableMaintenanceDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(TableMaintenanceDaoImpl.class);

	@Autowired
	protected HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link TableMaintenanceDaoImpl}.
	 */
	private TableMaintenanceDaoImpl() {
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
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Object[]> get(final String tableName, final String operation) {
		return (List<Object[]>) getSession().getNamedQuery(getSqlQueryName(tableName, operation)).list();
	}

	/**
	 * Method which returns the matching record for the code from given table
	 * maintenance table.
	 * 
	 * @return a list containing code and description pairs.
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Object[]> getByCode(final String tableName, final String operation, final String code) {
		return (List<Object[]>) getSession().getNamedQuery(getSqlQueryName(tableName, operation))
				.setParameter("CODE", Objects.requireNonNull(code, "Code cannot be null or emtpy."))				
				.list();
	}
	
	/**
	 * Method which creates a record in the given table maintenance table.
	 * 
	 * @return count of rows affected by the insert statement.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int create(final String tableName, final String operation, final String code, final String description) {		
		java.sql.Timestamp currentTimestamp = getCurrentTimeStamp();		
		return getSession().getNamedQuery(getSqlQueryName(tableName, operation))
				.setParameter("CODE", Objects.requireNonNull(code, "Code cannot be null or emtpy."))
				.setParameter("DESCRIPTION", Objects.requireNonNull(description, "Description cannot be null or emtpy."))
				.setTimestamp("CREATED_DT", currentTimestamp)
				.setParameter("CREATED_BY", "ADMIN")
				.setTimestamp("UPDATED_DT", currentTimestamp)
				.setParameter("UPDATED_BY", "ADMIN")
				.executeUpdate();
	}
	
	/**
	 * Method which updates a record in the given table maintenance table.
	 * 
	 * @return int count of rows affected by the update statement.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int update(final String tableName, final String operation, final String code, final String description) {		
		java.sql.Timestamp currentTimestamp = getCurrentTimeStamp();		
		return getSession().getNamedQuery(getSqlQueryName(tableName, operation))
				.setParameter("CODE", Objects.requireNonNull(code, "Code cannot be null or emtpy."))
				.setParameter("DESCRIPTION", Objects.requireNonNull(description, "Description cannot be null or emtpy."))
				.setTimestamp("UPDATED_DT", currentTimestamp)
				.setParameter("UPDATED_BY", "ADMIN")
				.executeUpdate();
	}
	
	/**
	 * Method which deletes record(s) in the given table maintenance table.
	 * 
	 * @return int count of rows affected by the delete statement.
	 */
	@Override
	public int delete(final String tableName, final String operation, final List<String> codes) {
		int result = 0;
		int startIndex = 0;
		
		while (startIndex < codes.size()) {
			
			final List<String> subList = codes.subList(startIndex, Math.min(codes.size(), startIndex + 100));
			
			result += getSession().getNamedQuery(getSqlQueryName(tableName, operation))
					.setParameterList("CODES", subList)
					.executeUpdate();
			
			startIndex += subList.size();			
		}
		
		return result;		 
	}
}
