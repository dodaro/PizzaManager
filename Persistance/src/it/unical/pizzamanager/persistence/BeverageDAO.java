package it.unical.pizzamanager.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class BeverageDAO {

	private static SessionFactory factory;

	public BeverageDAO() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		factory = configuration.buildSessionFactory(builder.build());

	}

	public void create(Beverage beverage) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.save(beverage);

		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Beverage> get() {
		Session session= factory.openSession();
		List<Beverage> bevereges=session.createSQLQuery("Select *from bevereges").addEntity(Beverage.class).list();
		return bevereges;
	}
}
