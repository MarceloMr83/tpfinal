package com.poo.tpfinal.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.entities.User;
import com.poo.tpfinal.repositories.BookingRepository;
import com.poo.tpfinal.utils.DateFormater;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
  
	@Autowired 
	private RoomService roomService;
  

	public List<Booking> retrieveAllBookings() {
		return bookingRepository.findAll();
	}
	
	public void addBooking(Booking booking) {
	    bookingRepository.save(booking);
	}
	
	public Booking getBooking(Long id) {
	    return bookingRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}
	
	public Booking replaceRoom(Booking booking, Long id) {
		return bookingRepository.findById(id)
	      .map(u -> {
            u.setCheckIn(booking.getCheckIn());
            u.setCheckOut(booking.getCheckOut());
            u.setCost(booking.getCost());
	        return bookingRepository.save(u);
	      })
	      .orElseGet(() -> {
	        return bookingRepository.save(booking);
	      });
	}

	public void deleteBooking(@PathVariable Long id) {
		bookingRepository.deleteById(id);
	}
	
	//costo del valor de la habitacion en dias de reserva
	public float calculateCost(String fromDate,String toDate,String price){
		long daydiff=1;
	   //formatea las fecha desde y hasta y la agrega la reserva
	   //convertir fecha dd-mm-yyyy a yyyy-mm-dd
		DateFormater dateFormat = new DateFormater();
		 Date from = dateFormat.getDate(fromDate);
		 Date to = dateFormat.getDate(toDate);
		 var diff = to.getTime() - from.getTime();  
		 //entrada y salida el mismo dia se cobra como un dia
		 if(diff ==0 ) {
			 diff=1;
		 }
		 daydiff = diff / (1000 * 60 * 60 * 24);    
	   return Float.parseFloat(price) * daydiff;  
	}

	//crea la instancia de la reserva
	public Booking newBooking(Long idRoom,String fromDate,String toDate,float cost,boolean parking,boolean breakfastIncluded,boolean freeCancelation){
		//crea la instancia del objeto reserva
		Booking booking = new Booking(); 
		//convertir fecha dd-mm-yyyy a yyyy-mm-dd
		DateFormater dateFormat = new DateFormater();
		Date createdAt = new Date(); 
		booking.setCreatedAt(createdAt);	   
		Date from = dateFormat.getDate(fromDate) ;
		Date to = dateFormat.getDate(toDate);
		booking.setCheckIn(from);
		booking.setCheckOut(to);
		  
	   //mostrar en la vista previa  
	   Room room = roomService.findById(idRoom);
	   //obtiene el objeto user como instancia de usuario logueado
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();	   
	   //todo - convertir en DTO objeto user
	   User guest = (User) auth.getPrincipal();	 
	    booking.setCreatedAt(createdAt);
	   booking.setGuest(guest);
	   booking.setRoom(room);
	   booking.setCost(cost);
	   booking.setBreakfastIncluded(breakfastIncluded);
	   booking.setParking(parking);
	   booking.setFreeCancelation(freeCancelation);
 		return booking;
	}

	public List<Booking> getAllUserBookings(String guest){
		return bookingRepository.retrieveAllUserBookings(guest);
	}

}
