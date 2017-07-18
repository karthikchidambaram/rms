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
 * {@link InvestigationTeam} and a {@link User} in a Role-Based Access Control
 * security design.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_INVST_TEAM_LEAD")
public class InvestigationTeamLead extends AbstractDataModel<InvestigationTeamLead.PrimaryKey> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary composite key for relationship. */
	private PrimaryKey _id;

	public InvestigationTeamLead() {
	}

	public InvestigationTeamLead(final User user, final InvestigationTeam investigationTeam) {
		_id = new PrimaryKey(user, investigationTeam);
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
	 * Returns the {@link InvestigationTeam} associated to the user.
	 * 
	 * @return investigationTeam
	 */
	@Transient
	public InvestigationTeam getInvestigationTeam() {
		return _id.getInvestigationTeam();
	}

	@Override
	public boolean equals(final Object obj) {
		return this == obj
				|| (obj instanceof InvestigationTeamLead && getId().equals(((InvestigationTeamLead) obj).getId()));
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public String toString() {
		return "Investigation Team -> Team Lead: " + _id.getInvestigationTeam().getInvestigationTeamDescription()
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
		/** InvestigationTeam of the relationship. */
		private InvestigationTeam _investigationTeam;

		/**
		 * Creates a new instance of {@code PrimaryKey}. Empty constructor
		 * required for Hibernate.
		 */
		public PrimaryKey() {
		}

		/**
		 * Creates a new instance of {@code PrimaryKey} for the specified
		 * {@code user} and {@code investigationTeam}.
		 * 
		 * @param user
		 * @param investigationTeam
		 */
		PrimaryKey(final User user, final InvestigationTeam investigationTeam) {
			_user = user;
			_investigationTeam = investigationTeam;
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
		@JoinColumn(name = "INVST_TEAM_ID")
		public InvestigationTeam getInvestigationTeam() {
			return _investigationTeam;
		}

		public void setInvestigationTeam(final InvestigationTeam investigationTeam) {
			_investigationTeam = investigationTeam;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			} else if (obj instanceof PrimaryKey) {
				final PrimaryKey key = (PrimaryKey) obj;
				return Objects.equals(_user, key.getUser())
						&& Objects.equals(_investigationTeam, key.getInvestigationTeam());
			}
			return false;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 79 * hash + Objects.hashCode(_user);
			hash = 79 * hash + Objects.hashCode(_investigationTeam);
			return hash;
		}

	}
}
