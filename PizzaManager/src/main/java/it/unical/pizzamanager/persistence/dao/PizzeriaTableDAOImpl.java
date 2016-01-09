package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.PizzeriaTable;
import it.unical.pizzamanager.persistence.dto.RelationBookingTablePizzeriaTable;

public class PizzeriaTableDAOImpl implements PizzeriaTableDAO {

	private DatabaseHandler databaseHandler;

	public PizzeriaTableDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(PizzeriaTable table) {
		databaseHandler.create(table);
	}

	@Override
	public void delete(PizzeriaTable table) {
		databaseHandler.delete(table);
	}

	@Override
	public void update(PizzeriaTable table) {
		databaseHandler.update(table);
	}

	@Override
	public PizzeriaTable get(Integer idTable) {
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from PizzeriaTable where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", idTable);
		PizzeriaTable table = (PizzeriaTable) query.uniqueResult();
		if (table != null) {
			for (RelationBookingTablePizzeriaTable relation : table.getBookings()) {
				Hibernate.initialize(relation);
			}
		}

		session.close();
		return table;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PizzeriaTable> getAllTablesList() {
		// TODO Auto-generated method stub
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from PizzeriaTable";
		Query query = session.createQuery(queryString);
		List<PizzeriaTable> tables = (List<PizzeriaTable>) query.list();
		if (tables != null) {
			for (PizzeriaTable t : tables) {
				t.getBookings().size();
			}
		}

		session.close();
		return tables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PizzeriaTable> getTablesOfPizzeria(Pizzeria pizzeria) {
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from PizzeriaTable where pizzeria = :pizzeria";
		Query query = session.createQuery(queryString);
		query.setParameter("pizzeria", pizzeria);
		List<PizzeriaTable> tables = (List<PizzeriaTable>) query.list();

		if (tables != null) {
			for (PizzeriaTable t : tables) {
				t.getBookings().size();
			}
		}

		session.close();
		return tables;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

}
