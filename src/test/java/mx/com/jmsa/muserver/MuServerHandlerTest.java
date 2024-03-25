package mx.com.jmsa.muserver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import mx.com.jmsa.muserver.entity.Reservation;

class MuServerHandlerTest {
	
	MuServerHandler muServerHandlerMock = Mockito.mock(MuServerHandler.class);

	@BeforeEach
	void setUp() throws Exception {
		Reservation reservationMock = new Reservation();
		reservationMock.setReservation_id((long) 44);
		reservationMock.setName("Johnny");
		reservationMock.setDate(null);
		reservationMock.setTime("10:00");
		reservationMock.setPartySize(4);
		
		List<Reservation> listReservationMock = new ArrayList<>();
		listReservationMock.add(reservationMock);
		
		Mockito.when(muServerHandlerMock.getAllReservations()).thenReturn(listReservationMock);
	}

	@Test
	void testGetAllReservations() {
		List<Reservation> listReservation = new ArrayList<>();
		listReservation = muServerHandlerMock.getAllReservations();
		Assertions.assertEquals("Johnny", listReservation.get(0).getName());
	}

}
