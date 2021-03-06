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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity representation of Claim Investigation Team.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_CLIM_INVST_TEAM")
@JsonIgnoreProperties({"claimInvestigationTeamLeads", "claimHandlers"})
public class ClaimInvestigationTeam extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary surrogate key ID of investigation team table. */
	private long _id;
	private String _claimInvestigationTeamName;
	private String _claimInvestigationTeamDescription;	
	/**
	 * Leader(s) of the team. Normally there will be only one leader for a team,
	 * but to allow flexibility and to accommodate future changes we are having
	 * the design to allow multiple users.
	 */
	private Set<User> _claimInvestigationTeamLeads = new HashSet<User>(0);
	private Set<User> _claimHandlers = new HashSet<User>(0);

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected ClaimInvestigationTeam() {
	}

	/**
	 * Creates a new immutable instance of {@link ClaimInvestigationTeam} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private ClaimInvestigationTeam(final Builder builder) {
		_claimInvestigationTeamName = Objects.requireNonNull(builder._claimInvestigationTeamName, "Claim investigation team name cannot be null.");
		_claimInvestigationTeamDescription = Objects.requireNonNull(builder._claimInvestigationTeamDescription, "Claim investigation team description cannot be null.");
	}

	/**
	 * Return the Claim Investigation Team primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_clim_invst_team_id_seq")
	@SequenceGenerator(name = "rms_clim_invst_team_id_seq", sequenceName = "RMS_CLIM_INVST_TEAM_ID_SEQ", allocationSize = 1)
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
	@Size(min = 1, max = 64, message = "Claim investigation team name must be between {min} and {max} characters")
	public String getClaimInvestigationTeamName() {
		return _claimInvestigationTeamName;
	}

	public void setClaimInvestigationTeamName(final String claimInvestigationTeamName) {
		_claimInvestigationTeamName = claimInvestigationTeamName;
	}

	@Column(name = "TEAM_DESC")
	@Size(min = 1, max = 128, message = "Claim investigation team description must be between {min} and {max} characters")
	public String getClaimInvestigationTeamDescription() {
		return _claimInvestigationTeamDescription;
	}

	public void setClaimInvestigationTeamDescription(final String claimInvestigationTeamDescription) {
		_claimInvestigationTeamDescription = claimInvestigationTeamDescription;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "RMS_CLIM_INVST_TEAM_LEAD", joinColumns = @JoinColumn(name = "CLIM_INVST_TEAM_ID"), inverseJoinColumns = @JoinColumn(name = "USR_ID"))
	public Set<User> getClaimInvestigationTeamLeads() {
		return _claimInvestigationTeamLeads;		
	}

	public void setClaimInvestigationTeamLeads(final Set<User> claimInvestigationTeamLeads) {
		_claimInvestigationTeamLeads = claimInvestigationTeamLeads;
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "RMS_CLIM_HNDLRS", joinColumns = @JoinColumn(name = "CLIM_INVST_TEAM_ID"), inverseJoinColumns = @JoinColumn(name = "USR_ID"))
	public Set<User> getClaimHandlers() {
		return _claimHandlers;		
	}

	public void setClaimHandlers(final Set<User> claimHandlers) {
		_claimHandlers = claimHandlers;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link ClaimInvestigationTeam}.
	 */
	public final static class Builder {

		private String _claimInvestigationTeamName;
		private String _claimInvestigationTeamDescription;

		/**
		 * Builds a new immutable instance of ClaimInvestigationTeam.
		 * 
		 * @return new instance of ClaimInvestigationTeam
		 */
		public ClaimInvestigationTeam build() {
			return new ClaimInvestigationTeam(this);
		}

		public Builder setClaimInvestigationTeamName(final String claimInvestigationTeamName) {
			_claimInvestigationTeamName = claimInvestigationTeamName;
			return this;
		}

		public Builder setClaimInvestigationTeamDescription(final String claimInvestigationTeamDescription) {
			_claimInvestigationTeamDescription = claimInvestigationTeamDescription;
			return this;
		}
	}
}
