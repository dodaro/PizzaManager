package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

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

		String queryString = "from TablePizzeria where id = :id_table";
		Query query = session.createQuery(queryString);
		query.setParameter("id_table", idTable);
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

		String queryString = "from TablePizzeria";
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

	/*
	 * ho commentato questi metodi in attesa che dodaro mi confermi che non servono. come ne avrò la
	 * certezza li cancello.
	 * 
	 */

	// @Override
	// public TablePizzeria getFromNumber(/*Pizzeria pizzeria,*/Integer number) {
	// // TODO Togliere i commenti dopo aver creato l'entità pizzeria
	// Session session = databaseHandler.getSessionFactory().openSession();
	//
	// String queryString = "from TablePizzeria where number = :number_table"/*+" and pizzeria =
	// :pizzeria_table"*/;
	// Query query = session.createQuery(queryString);
	// query.setParameter("number_table", number);
	// //query.setParameter("pizzeria_table", pizzeria);
	// TablePizzeria table= (TablePizzeria) query.uniqueResult();
	// if (table != null) {
	// for (RelationTableBookingTablePizzeria relation : table.getTableBooking()) {
	// Hibernate.initialize(relation);
	// }
	// }
	//
	// session.close();
	// return table;
	// }
	//
	//
	// @SuppressWarnings("unchecked")
	// @Override
	// public List<TablePizzeria> getTablesList(/*Pizzeria pizzeria*/) {
	// // TODO Auto-generated method stub
	// Session session = databaseHandler.getSessionFactory().openSession();
	//
	// String queryString = "from TablePizzeria"/*+" where pizzeria = : pizzeria_table"*/;
	// Query query = session.createQuery(queryString);
	// //query.setParameter("pizzeria_table", pizzeria);
	// List<TablePizzeria> tables = (List<TablePizzeria>) query.list();
	// if(tables!=null){
	// for (TablePizzeria t : tables) {
	// t.getTableBooking().size();
	// }
	// }
	//
	// session.close();
	// return tables;
	// }

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

}
