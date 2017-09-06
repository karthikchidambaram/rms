package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Entity representation of Investigation Team.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_INVST_TEAM")
public class InvestigationTeam extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary surrogate key ID of investigation team table. */
	private long _id;
	private String _investigationTeamName;
	private String _investigationTeamDescription;	
	/**
	 * Leader(s) of the team. Normally there will be only one leader for a team,
	 * but to allow flexibility and to accommodate future changes we are having
	 * the design to allow multiple users.
	 */
	private Set<User> _users = new HashSet<User>(0);

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected InvestigationTeam() {
	}

	/**
	 * Creates a new immutable instance of {@link InvestigationTeam} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private InvestigationTeam(final Builder builder) {
		_investigationTeamName = Objects.requireNonNull(builder._investigationTeamName, "Investigation team name cannot be null.");
		_investigationTeamDescription = Objects.requireNonNull(builder._investigationTeamDescription, "Investigation team description cannot be null.");
	}

	/**
	 * Return the Investigation Team primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_invst_team_id_seq")
	@SequenceGenerator(name = "rms_invst_team_id_seq", sequenceName = "RMS_INVST_TEAM_ID_SEQ", allocationSize = 1)
	@Override
	public Long getId() {
		return _id;
	}

	/**
	 * Sets the primary surrogate key ID.
	 * 
	 * <p>
	 * <strong>Note:</strong> This method has protected access to prevent
	 * callers from manually setting the auto-generated primary key ID;
	 * Hibernate has access to invoke this method when populating an entity.
	 * </p>
	 * 
	 * @param id
	 */
	protected void setId(final long id) {
		_id = id;
	}

	@Column(name = "TEAM_NAM")
	@Size(min = 1, max = 64, message = "Investigation team name must be between {min} and {max} characters")
	public String getInvestigationTeamName() {
		return _investigationTeamName;
	}

	public void setInvestigationTeamName(final String investigationTeamName) {
		_investigationTeamName = investigationTeamName;
	}

	@Column(name = "TEAM_DESC")
	@Size(min = 1, max = 128, message = "Investigation team description must be between {min} and {max} characters")
	public String getInvestigationTeamDescription() {
		return _investigationTeamDescription;
	}

	public void setInvestigationTeamDescription(final String investigationTeamDescription) {
		_investigationTeamDescription = investigationTeamDescription;
	}

	/**
	 * Returns the set of {@code User}s which are associated with this
	 * InvestigationTeam.
	 * 
	 * @return set of associated users
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_INVST_TEAM_LEAD", 
			joinColumns = @JoinColumn(name = "INVST_TEAM_ID"), 
			inverseJoinColumns = @JoinColumn(name = "USR_ID")
	)
	public Set<User> getUsers() {
		return _users;
	}

	/**
	 * Sets the set of {@code User}s which are associated to this investigation
	 * team.
	 * 
	 * <p>
	 * <strong>Note:</strong> This method has protected access to prevent
	 * callers from manually setting the permissions as Roles should never be
	 * created/updated programmatically; Hibernate has access to invoke this
	 * method when populating an entity.
	 * </p>
	 * 
	 * @param users
	 */
	public void setUsers(final Set<User> users) {
		this._users = users;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link InvestigationTeam}.
	 */
	public final static class Builder {

		private String _investigationTeamName;
		private String _investigationTeamDescription;

		/**
		 * Builds a new immutable instance of InvestigationTeam.
		 * 
		 * @return new instance of InvestigationTeam
		 */
		public InvestigationTeam build() {
			return new InvestigationTeam(this);
		}

		public Builder setInvestigationTeamName(final String investigationTeamName) {
			_investigationTeamName = investigationTeamName;
			return this;
		}

		public Builder setInvestigationTeamDescription(final String investigationTeamDescription) {
			_investigationTeamDescription = investigationTeamDescription;
			return this;
		}
	}
}
