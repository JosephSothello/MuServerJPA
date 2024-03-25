package mx.com.jmsa.muserver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mx.com.jmsa.muserver.dao.ReservationDao;
import mx.com.jmsa.muserver.dto.ReservationResponseDto;
import mx.com.jmsa.muserver.entity.Reservation;

@Path("/mu")
public class MuServerHandler {

	private ReservationDao reservationDao = new ReservationDao();

	@GET
	@Path("/reservations")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reservation> getAllReservations() {
		return reservationDao.getAllReservations();
	}

	@POST
	@Path("/booking")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReservationResponseDto addBooking(Reservation reservation) {
		ReservationResponseDto dto = new ReservationResponseDto();
		
		//Hack
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(reservation.getDate());
		calDate.add(Calendar.DAY_OF_MONTH, 1);
		reservation.setDate(calDate.getTime());
		
		if (reservationDao.addBooking(reservation)) {
			dto.setSuccess(true);
			dto.setMessage("Booking added successfully");	//For future dinamic messages
		} else {
			dto.setSuccess(false);
			dto.setMessage("Failed to add booking");		//For future dinamic messages
		}
		return dto;
	}
	
	@GET
	@Path("/getAvailableTimes/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAvailableTimes(@PathParam("date") String stringDate) {
		List<String> availableTimes = new ArrayList<>(Arrays.asList("10:00", "12:00", "14:00", "16:00", "18:00"));
//		LocalDate localDate = LocalDate.parse(stringDate);
//		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat sDateF = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sDateF.parse(stringDate);
//			date = sDateF.parse("2024-03-24");
			List<String> bookedTimes = reservationDao.getBookedTimes(date);
			availableTimes.removeAll(bookedTimes);
			return availableTimes;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
