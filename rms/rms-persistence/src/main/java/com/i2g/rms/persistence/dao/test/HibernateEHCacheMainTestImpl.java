package com.i2g.rms.persistence.dao.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.test.MyEmployee;

@Repository
public class HibernateEHCacheMainTestImpl implements HibernateEHCacheMainTest {

	/** Hibernate session factory for executing Hibernate queries. */
	@Autowired
	protected SessionFactory sessionFactory;
	
	@Override
	public String testSecondLevelCache() {

		System.out.println("Temp Dir: " + System.getProperty("java.io.tmpdir"));

		// Initialize Sessions
		Statistics stats = sessionFactory.getStatistics();
		System.out.println("Stats enabled = " + stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
		System.out.println("Stats enabled = " + stats.isStatisticsEnabled());

		Session session = sessionFactory.openSession();
		Session otherSession = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		Transaction otherTransaction = otherSession.beginTransaction();

		printStats(stats, 0);

		MyEmployee emp = (MyEmployee) session.load(MyEmployee.class, 7839);
		printData(emp, stats, 1);

		emp = (MyEmployee) session.load(MyEmployee.class, 7839);
		printData(emp, stats, 2);

		// clear first level cache, so that second level cache is used
		session.evict(emp);
		emp = (MyEmployee) session.load(MyEmployee.class, 7839);
		printData(emp, stats, 3);

		emp = (MyEmployee) session.load(MyEmployee.class, 7698);
		printData(emp, stats, 4);

		emp = (MyEmployee) otherSession.load(MyEmployee.class, 7839);
		printData(emp, stats, 5);

		// Release resources
		transaction.commit();
		otherTransaction.commit();
		
		return "Test completed..";
	}

	private static void printStats(Statistics stats, int i) {
		System.out.println("***** " + i + " *****");
		System.out.println("Fetch Count = " + stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count = " + stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count = " + stats.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count = " + stats.getSecondLevelCachePutCount());
	}

	private static void printData(MyEmployee emp, Statistics stats, int count) {
		System.out.println(count + ":: Name = " + emp.getEName() + ", Department = " + emp.getDepartment().getDname());
		printStats(stats, count);
	}
}
