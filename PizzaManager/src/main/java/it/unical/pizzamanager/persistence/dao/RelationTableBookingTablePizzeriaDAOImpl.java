package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.RelationBookingTablePizzeriaTable;
import it.unical.pizzamanager.persistence.dto.PizzeriaTable;

public class RelationTableBookingTablePizzeriaDAOImpl implements RelationTableBookingTablePizzeriaDAO{

	private DatabaseHandler databaseHandler;
		
	public RelationTableBookingTablePizzeriaDAOImpl() {
		// TODO Auto-generated constructor stub
		databaseHandler=null;
	}
	
	@Override
	public void create(RelationBookingTablePizzeriaTable table_booking) {
		// TODO Auto-generated method stub
		databaseHandler.create(table_booking);
	}

	@Override
	public void delete(RelationBookingTablePizzeriaTable table_booking) {
		// TODO Auto-generated method stub
		databaseHandler.delete(table_booking);
	}

	@Override
	public void update(RelationBookingTablePizzeriaTable table_booking) {
		// TODO Auto-generated method stub
		databaseHandler.update(table_booking);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelationBookingTablePizzeriaTable> getRelationTableBookingTablePizzeriaList() {
		// TODO Auto-generated method stub
		Session session = databaseHandler.getSessionFactory().openSession();
		
		String queryString = "from RelationTableBookingTablePizzeria";
		Query query = session.createQuery(queryString);
		List<RelationBookingTablePizzeriaTable> tableBooking = (List<RelationBookingTablePizzeriaTable>) query.list();		
		session.close();
		return tableBooking;
	}

}
