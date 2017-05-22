package com.i2g.rms.rest.model.search;

import java.util.Collections;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Karthikeyan Chidambaram
 */
public abstract class SearchBuilder<T> {
	private final DetachedCriteria criteria;

	public SearchBuilder(DetachedCriteria criteria) {
		this.criteria = criteria;
	}
		
	protected DetachedCriteria filterBy(List<FilterRO> filters) {
		for (FilterRO filter : filters) {

			String field = filter.getField();
			if ("username".equalsIgnoreCase(field)) {
				field = "id";
			}

			FilterRO.Operator operator = filter.getOperator();
			String value = filter.getValue();
			switch (operator) {
				case EQ:
					criteria.add(Restrictions.eq(field, value));
					break;
				case NEQ:
					criteria.add(Restrictions.ne(field, value));
					break;
				case STARTSWITH:
					criteria.add(Restrictions.ilike(field, value + "%"));
					break;
				case ENDSWITH:
					criteria.add(Restrictions.ilike(field, "%" + value));
					break;
				case CONTAINS:
					criteria.add(Restrictions.ilike(field, "%" + value + "%"));
					break;
				case DOESNOTCONTAIN:
					criteria.add(Restrictions.not(Restrictions.ilike(field, "%" + value + "%")));
					break;
			}
		}

		return criteria;
	}
	
    protected DetachedCriteria orderedBy(List<SortRO> sorts) {
		for (SortRO sort : sorts) {
			String field = sort.getField();
			if ("username".equalsIgnoreCase(sort.getField())) {
				field = "id";
			}

			criteria.addOrder(sort.isAsc() ? Order.asc(field).ignoreCase() : Order.desc(field).ignoreCase());
		}
		
		return criteria;
	}
	
    protected List<T> result() {
		return Collections.emptyList();
	}
}
