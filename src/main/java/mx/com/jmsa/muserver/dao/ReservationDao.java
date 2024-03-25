package mx.com.jmsa.muserver.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;

import mx.com.jmsa.muserver.entity.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ReservationDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("yourPersistenceUnit");
	EntityManager em = emf.createEntityManager();

	public List<Reservation> getAllReservations() {
		em.getTransaction().begin();
		List<Reservation> entities = em.createQuery("SELECT r FROM Reservation r ORDER BY r.date ASC, r.time ASC", Reservation.class).getResultList();
		em.getTransaction().commit();
		return entities;

//    	Reservation reservation = new Reservation();
//    	
//    	reservation.setReservationId(1L);
//    	reservation.setDate(new Date());
//    	reservation.setStarting("10:00");
//    	reservation.setEnding("12:00");
//    	reservation.setPartySize(2);
//    	reservation.setStatus("Booked");
//    	reservation.setUserId(1L);
//    	reservation.setTableId(1L);
//    	
//    	List<Reservation> reservationList = new ArrayList<Reservation>();
//    	reservationList.add(reservation);
//    	return reservationList;

	}

	public boolean addBooking(Reservation reservation) {
		// Begin a transaction, persist the entity, and commit the transaction
		try {
			em.getTransaction().begin();
			em.persist(reservation);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback(); // Rollback the transaction on error
			}
			e.printStackTrace(); // Log the exception or handle it as appropriate
			return false; // Indicate failure
		}
	}

	public List<String> getBookedTimes(Date date) {
		// TODO Auto-generated method stub
		String hql = "SELECT r.time FROM Reservation r WHERE r.date = :date";
		em.getTransaction().begin();
		Query<String> query = (Query<String>) em.createQuery(hql, String.class);
		query.setParameter("date", date);
        List<String> bookedTimes = query.getResultList();
		em.getTransaction().commit();
		return bookedTimes;
	}

}
