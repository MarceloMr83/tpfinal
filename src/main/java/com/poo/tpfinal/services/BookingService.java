package com.poo.tpfinal.services;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.repositories.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;

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
	
/*	public User findUserByUsername(String username) {
		//return userRepository.findUserByUsername(username);
	}*/
	
}
