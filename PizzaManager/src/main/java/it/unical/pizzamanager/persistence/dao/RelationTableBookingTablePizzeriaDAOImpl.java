package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.PizzeriaTable;
import it.unical.pizzamanager.persistence.dto.RelationBookingTablePizzeriaTable;

public class RelationTableBookingTablePizzeriaDAOImpl
		implements RelationTableBookingTablePizzeriaDAO {

	private DatabaseHandler databaseHandler;

	public RelationTableBookingTablePizzeriaDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(RelationBookingTablePizzeriaTable table_booking) {
		databaseHandler.create(table_booking);
	}

	@Override
	public void delete(RelationBookingTablePizzeriaTable table_booking) {
		databaseHandler.delete(table_booking);
	}

	@Override
	public void update(RelationBookingTablePizzeriaTable table_booking) {
		databaseHandler.update(table_booking);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelationBookingTablePizzeriaTable> getRelationTableBookingTablePizzeriaList() {
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from RelationBookingTablePizzeriaTable";
		Query query = session.createQuery(queryString);
		List<RelationBookingTablePizzeriaTable> tableBooking = (List<RelationBookingTablePizzeriaTable>) query
				.list();
		session.close();
		return tableBooking;
	}

	@SuppressWarnings("unchecked")
	public List<RelationBookingTablePizzeriaTable> getByTable(PizzeriaTable table) {
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from RelationBookingTablePizzeriaTable where table = :table";
		Query query = session.createQuery(queryString);
		query.setParameter("table", table);
		List<RelationBookingTablePizzeriaTable> tableBooking = (List<RelationBookingTablePizzeriaTable>) query
				.list();
		session.close();
		return tableBooking;

	}
}
