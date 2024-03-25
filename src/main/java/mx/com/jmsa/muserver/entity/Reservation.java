package mx.com.jmsa.muserver.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservation_id;

    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(nullable = false, length = 10)
    private String time;

//    @Column(nullable = false, length = 10)
//    private String ending;

    @Column(nullable = false)
    private Integer party_size;

//    @Column(nullable = false, length = 50)
//    private String status;
    
//    @Column(nullable = false)
//    private Long user_id;
    
//    @Column(nullable = false)
//    private Long table_id;

    // Constructors
	public Reservation() {
	}

	// Getters and Setters
	public Long getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Long reservation_id) {
		this.reservation_id = reservation_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getPartySize() {
		return party_size;
	}

	public void setPartySize(Integer party_size) {
		this.party_size = party_size;
	}

	@Override
	public String toString() {
		return "Reservation [reservation_id=" + reservation_id + ", name=" + name + ", date=" + date + ", time=" + time
				+ ", party_size=" + party_size + "]";
	}

//	public Reservation(Date date, String starting, String ending, Integer party_size, String status, Long user_id,
//			Long table_id) {
//		this.date = date;
//		this.starting = starting;
//		this.ending = ending;
//		this.party_size = party_size;
//		this.status = status;
//		this.user_id = user_id;
//		this.table_id = table_id;
//	}
	
	

	
	


	
}
