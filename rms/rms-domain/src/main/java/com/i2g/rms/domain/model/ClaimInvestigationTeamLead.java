package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Relational entity for representing the relationship between a
 * {@link ClaimInvestigationTeam} and a {@link User} in a Role-Based Access Control
 * security design.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_CLIM_INVST_TEAM_LEAD")
public class ClaimInvestigationTeamLead extends AbstractDataModel<ClaimInvestigationTeamLead.PrimaryKey> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary composite key for relationship. */
	private PrimaryKey _id;

	public ClaimInvestigationTeamLead() {
	}

	public ClaimInvestigationTeamLead(final User user, final ClaimInvestigationTeam claimInvestigationTeam) {
		_id = new PrimaryKey(user, claimInvestigationTeam);
	}

	@EmbeddedId
	@Override
	public PrimaryKey getId() {
		return _id;
	}

	public void setId(final PrimaryKey id) {
		_id = id;
	}

	/**
	 * Returns the {@link User} associated to the investigation team.
	 * 
	 * @return user
	 */
	@Transient
	public User getUser() {
		return _id.getUser();
	}

	/**
	 * Returns the {@link ClaimInvestigationTeam} associated to the user.
	 * 
	 * @return claimInvestigationTeam
	 */
	@Transient
	public ClaimInvestigationTeam getClaimInvestigationTeam() {
		return _id.getClaimInvestigationTeam();
	}

	@Override
	public boolean equals(final Object obj) {
		return this == obj
				|| (obj instanceof ClaimInvestigationTeamLead && getId().equals(((ClaimInvestigationTeamLead) obj).getId()));
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public String toString() {
		return "Claim Investigation Team -> Team Lead: " + _id.getClaimInvestigationTeam().getClaimInvestigationTeamDescription()
				+ " -> " + _id.getUser().getLoginId();
	}

	/**
	 * Primary key ID for the user/team relationship.
	 */
	@Embeddable
	public static class PrimaryKey implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/** User of the relationship. */
		private User _user;
		/** ClaimInvestigationTeam of the relationship. */
		private ClaimInvestigationTeam _claimInvestigationTeam;

		/**
		 * Creates a new instance of {@code PrimaryKey}. Empty constructor
		 * required for Hibernate.
		 */
		public PrimaryKey() {
		}

		/**
		 * Creates a new instance of {@code PrimaryKey} for the specified
		 * {@code user} and {@code claimInvestigationTeam}.
		 * 
		 * @param user
		 * @param claimInvestigationTeam
		 */
		PrimaryKey(final User user, final ClaimInvestigationTeam claimInvestigationTeam) {
			_user = user;
			_claimInvestigationTeam = claimInvestigationTeam;
		}

		@ManyToOne
		@JoinColumn(name = "USR_ID")
		public User getUser() {
			return _user;
		}

		public void setUser(final User user) {
			_user = user;
		}

		@ManyToOne
		@JoinColumn(name = "CLIM_INVST_TEAM_ID")
		public ClaimInvestigationTeam getClaimInvestigationTeam() {
			return _claimInvestigationTeam;
		}

		public void setClaimInvestigationTeam(final ClaimInvestigationTeam claimInvestigationTeam) {
			_claimInvestigationTeam = claimInvestigationTeam;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			} else if (obj instanceof PrimaryKey) {
				final PrimaryKey key = (PrimaryKey) obj;
				return Objects.equals(_user, key.getUser())
						&& Objects.equals(_claimInvestigationTeam, key.getClaimInvestigationTeam());
			}
			return false;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 79 * hash + Objects.hashCode(_user);
			hash = 79 * hash + Objects.hashCode(_claimInvestigationTeam);
			return hash;
		}

	}
}
