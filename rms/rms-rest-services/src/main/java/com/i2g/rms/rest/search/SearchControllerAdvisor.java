package com.i2g.rms.rest.search;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationClassFilter;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of {@link AbstractPoincutAdvisor} for defining the pointcuts
 * and advice to apply for supporting REST controller search support.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Service
public class SearchControllerAdvisor extends AbstractPointcutAdvisor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Method interceptor to apply for matched pointcuts. */
	@Autowired
	private SearchMethodInterceptor _interceptor;
	/**
	 * Pointcut which only matches annotated REST controller methods which are
	 * annotated with {@link Filterable}, {@link Sortable}, {@link Pageable} and
	 * {@link Searchable}.
	 */
	private final Pointcut _pointcut = new ComposablePointcut(new AnnotationClassFilter(RestController.class))
			.intersection(
					new ComposablePointcut(new AnnotationMethodMatcher(Filterable.class))
							.union(new AnnotationMethodMatcher(Sortable.class))
							.union(new AnnotationMethodMatcher(Pageable.class))
							.union(new AnnotationMethodMatcher(Searchable.class))
			);
	
	
	@Override
	public Pointcut getPointcut() {
		return _pointcut;
	}
	
	@Override
	public Advice getAdvice() {
		return _interceptor;
	}
	
}
