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
import org.springframework.web.context.request.ServletRequestAttributes;

import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.entities.User;
import com.poo.tpfinal.repositories.BookingRepository;

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

	public Booking newBooking(String idRoom,String fromDate,String toDate,String price){
		long daydiff=1;
		//crea la instancia del objeto reserva
		Booking booking = new Booking(); 
	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	   //formatea las fecha desde y hasta y la agrega la reserva
	   try {
		 Date createdAt = new Date(); 
		 booking.setCreatedAt(createdAt);   
		 Date from = dateFormat.parse(fromDate);
		 Date to = dateFormat.parse(toDate);
		 var diff = to.getTime() - from.getTime();   
		 daydiff = diff / (1000 * 60 * 60 * 24); 
		 booking.setCheckIn(from);
		 booking.setCheckOut(to);
	   } 
	   catch (ParseException e) {
			   e.printStackTrace();
		   }	   
	   float cost = Float.parseFloat(price) * daydiff;  
	   //mostrar en la vista previa  
	   Room room = roomService.findById(idRoom);
 
	   //obtiene el objeto user como instancia de usuario logueado
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();	   
	   //todo - convertir en DTO objeto user
	   User guest = (User) auth.getPrincipal();
	 
	   booking.setGuest(guest);
	   booking.setRoom(room);
	   booking.setCost(cost);
 		return booking;
	}
	
}
