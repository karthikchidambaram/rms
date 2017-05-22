package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.Message;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation of {@code MessageDao} for the {@code Message} entity.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Repository
public class MessageDaoImpl extends AbstractHibernateDao<Integer, Message> implements MessageDao {
	/**
	 * Creates a new instance of {@code MessageDaoImpl}.
	 */
	public MessageDaoImpl() {
		super(Message.class);
	}

	@Override
	public List<Message> getMessages() {
		return applySearch(getSession().createCriteria(_modelType)).list();
	}

	@Override
	public boolean exists(final Integer id) {
		return (Long) getSession().createCriteria(_modelType)
				.add(Restrictions.eq("id", Objects.requireNonNull(id, "Message ID cannot be null")))
				.setProjection(Projections.rowCount()).uniqueResult() != 0;
	}

	@Override
	public Message create(final Message message) {
		save(message);
		return message;
	}
}
