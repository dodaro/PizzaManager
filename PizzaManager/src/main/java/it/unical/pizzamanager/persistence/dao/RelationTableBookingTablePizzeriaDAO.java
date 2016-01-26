package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.entities.RelationBookingTablePizzeriaTable;

public interface RelationTableBookingTablePizzeriaDAO {
	
	public void create(RelationBookingTablePizzeriaTable table_booking);
	
	public void delete(RelationBookingTablePizzeriaTable table_booking);
	
	public void update(RelationBookingTablePizzeriaTable table_booking);
	
	public List<RelationBookingTablePizzeriaTable> getRelationTableBookingTablePizzeriaList();
	
	/*
	 * 
	 * se metto nel carrello tante pizze e bibite diverse relative a diverse pizzerie alla fine avrò 
	 * una singola prenotazione per ogni pizzeria, e dentro ogni prenotazione ci sarà un order
	 *  i cui item saranno relativi, per l'appunto, alla singola pizzeria.
	 *  
	 *  Per cui nonostante ci sia ridondanza è meglio avere direttamente la pizzeria nella prenotazione,
	 *  altrimenti tocca ricavare le prenotazioni di una pizzeria in maniera incasinata, tipo accedendo
	 *  all'order di ogni booking, e per almeno un order item devo vedere a quale pizzeria appartiene.
	 *  
	 *  IMPORTANTE: inoltre se non abbiamo pizzeria dentro booking sarà compito della nostra logica garantire
	 *  che una singola prenotazione deve avere order i cui item sono relativi alla stessa pizzeria.
	 *  
	 *   
	 *  Come è strutturato adesso stiamo dicendo che una singola prenotazione potrebbe avere item di pizzerie diverse
	 *  e non va bene in quanto la tabella booking possiede le informazioni riguardo cosa è stato prenotato in una certa pizzeria.
	 *  
	 *    
	 *  MOLTO SENSATO CHE LA PIZZERIA ABBIA LA RELAZIONE 1 A MOLTI SU BOOKING.
	 *  BOOKING INVECE AVRÀ 1 A 1 SU PIZZERIA
	 *  
	 *  
	 *  
	 */
	
}
