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

import com.i2g.rms.domain.model.tablemaintenance.BodyPart;

/**
 * Relational entity for representing the relationship between a
 * {@link BodyPart} and a {@link InjuredPerson}.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_INJRD_PRSN_BDY_PRTS")
public class InjuredPersonBodyParts extends AbstractDataModel<InjuredPersonBodyParts.PrimaryKey>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary composite key for relationship. */
	private PrimaryKey _id;

	public InjuredPersonBodyParts() {
	}

	public InjuredPersonBodyParts(final BodyPart bodyParts, final InjuredPerson injuredPerson) {
		_id = new PrimaryKey(bodyParts, injuredPerson);
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
	 * Returns the {@link BodyPart} associated to injured person.
	 * 
	 * @return bodyParts
	 */
	@Transient
	public BodyPart getBodyParts() {
		return _id.getBodyParts();
	}

	/**
	 * Returns the {@link InjuredPerson} associated to the BodyParts.
	 * 
	 * @return injuredPerson
	 */
	@Transient
	public InjuredPerson getInjuredPerson() {
		return _id.getInjuredPerson();
	}

	@Override
	public boolean equals(final Object obj) {
		return this == obj
				|| (obj instanceof InjuredPersonBodyParts && getId().equals(((InjuredPersonBodyParts) obj).getId()));
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public String toString() {
		return "Injured Person -> Body Parts: " + _id.getInjuredPerson().getFirstName() + " "
				+ _id.getInjuredPerson().getLastName() + " -> " + _id.getBodyParts().getDescription();
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

		/** BodyParts of the relationship. */
		private BodyPart _bodyParts;
		/** InjuredPerson of the relationship. */
		private InjuredPerson _injuredPerson;

		/**
		 * Creates a new instance of {@code PrimaryKey}. Empty constructor
		 * required for Hibernate.
		 */
		public PrimaryKey() {
		}

		/**
		 * Creates a new instance of {@code PrimaryKey} for the specified
		 * {@code bodyParts} and {@code injuredPerson}.
		 * 
		 * @param bodyParts
		 * @param injuredPerson
		 */
		PrimaryKey(final BodyPart bodyParts, final InjuredPerson injuredPerson) {
			_bodyParts = bodyParts;
			_injuredPerson = injuredPerson;
		}

		@ManyToOne
		@JoinColumn(name = "BDY_PRTS_CDE")
		public BodyPart getBodyParts() {
			return _bodyParts;
		}

		public void setBodyParts(final BodyPart bodyParts) {
			_bodyParts = bodyParts;
		}

		@ManyToOne
		@JoinColumn(name = "INJRD_PRSN_ID")
		public InjuredPerson getInjuredPerson() {
			return _injuredPerson;
		}

		public void setInjuredPerson(final InjuredPerson injuredPerson) {
			_injuredPerson = injuredPerson;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			} else if (obj instanceof PrimaryKey) {
				final PrimaryKey key = (PrimaryKey) obj;
				return Objects.equals(_bodyParts, key.getBodyParts())
						&& Objects.equals(_injuredPerson, key.getInjuredPerson());
			}
			return false;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 79 * hash + Objects.hashCode(_bodyParts);
			hash = 79 * hash + Objects.hashCode(_injuredPerson);
			return hash;
		}
	}
}
