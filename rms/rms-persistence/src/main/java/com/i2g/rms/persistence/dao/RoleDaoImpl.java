package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.Permission;
import com.i2g.rms.domain.model.Role;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for RoleDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class RoleDaoImpl extends AbstractHibernateDao<Long, Role> implements RoleDao {

	private final Logger _logger = LoggerFactory.getLogger(RoleDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link RoleDaoImpl}.
	 */
	private RoleDaoImpl() {
		super(Role.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all roles
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles() {
		return (List<Role>) applySearch(getSession().createCriteria(_modelType)).list();
	}
	
	@Override
	public Role getRoleById(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Role id cannot be null or empty.")));
		return (Role) criteria.uniqueResult();
	}

	@Override
	public void deleteRole(final Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoles(final List<Role> roles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePermissionFromRole(final Role role, final Permission permission) {
		validateObject(role);
		validateObject(permission);
		role.getPermissions().remove(permission);
		save(role);
	}

	@Override
	public void deletePermissionsFromRole(final Role role, final List<Permission> permissions) {
		validateObject(role);
		validateCollectionObject(permissions);
		for (Permission permission : permissions) {
			if (permission != null) {
				role.getPermissions().remove(permission);
				save(role);
			}
		}
	}

	@Override
	public Role getRoleByRoleName(final String roleName) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("roleName", Objects.requireNonNull(roleName, "Role name cannot be null or empty.")));
		return (Role) criteria.uniqueResult();
	}	
}
