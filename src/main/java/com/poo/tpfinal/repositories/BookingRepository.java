package com.poo.tpfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.poo.tpfinal.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

    @Query(value="SELECT b.idBooking,r.name,b.checkIn,b.checkOut,b.createdAt, b.breakfastIncluded,b.parking, \r\n" +
    "b.freeCancelation,b.cost FROM room r,booking b WHERE b.room=r.idRoom AND b.guest= :guest", nativeQuery = true)
	  List<Booking> retrieveAllUserBookings(String guest);
		
}
