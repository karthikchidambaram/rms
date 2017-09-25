package com.i2g.rms.rest.service.incident;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Role;
import com.i2g.rms.domain.model.Subordinate;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.incident.SearchIncident;
import com.i2g.rms.rest.constants.RoleConstants;
import com.i2g.rms.rest.model.incident.SearchIncidentRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.service.incident.SearchIncidentService;
import com.i2g.rms.service.lookup.SubordinateLookupService;

/**
 * Rest services for search incident.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class SearchIncidentRestServiceImpl extends AbstractRestService implements SearchIncidentRestService {

	@Autowired
	private SearchIncidentService _searchIncidentService;
	@Autowired
	private SubordinateLookupService _subordinateLookupService;

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<SearchIncidentRO> get() {
		// Get the logged in user from context
		final User user = getUserFromContext();
		validateGenericObject(user);

		// Get the logged in user id from context and start building the list of
		// user login ids.
		final Set<String> loginIds = new HashSet<String>(0);
		loginIds.add(user.getLoginId());

		// Check if the logged in user is a manager and he/she has subordinates.
		// If yes, then add the subordinates login ids to the list
		loginIds.addAll(buildSubordinateLoginIds(_subordinateLookupService.get(user.getLoginId())));
		List<SearchIncident> searchIncidents = _searchIncidentService.get(loginIds, isAdminUser(user.getRoles()));
		List<SearchIncidentRO> searchIncidentROs = searchIncidents.isEmpty() ? Collections.emptyList() : _mapperService.map(searchIncidents, SearchIncidentRO.class);
		return searchIncidentROs;
	}

	private Set<String> buildSubordinateLoginIds(final List<Subordinate> subordinates) {
		final Set<String> subordinateLoginIds = new HashSet<String>(0);
		if (subordinates != null && !subordinates.isEmpty()) {
			for (Subordinate subordinate : subordinates) {
				subordinateLoginIds.add(subordinate.getUserLoginId());
			}
		}
		return subordinateLoginIds;
	}
	
	private boolean isAdminUser(final Set<Role> roles) {
		boolean result = false;		
		if (roles != null && !roles.isEmpty()) {
			for (Role role : roles) {
				if (role.getRoleName().equals(RoleConstants.ADMIN_ACCESS)) {
					result = true;
					break;
				}
			}
		}	
		return result;
	}
}
