package com.poo.tpfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.dto.BookingDTO;
import com.poo.tpfinal.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

    @Query(value="SELECT b.idBooking,r.name,DATE_FORMAT(b.checkIn,'%d-%m-%Y') as checkIn,  \r\n" +
     "DATE_FORMAT(b.checkOut,'%d-%m-%Y') as checkOut,DATE_FORMAT(b.createdAt,'%d-%m-%Y') as createdAt, \r\n" +
     " IF(b.breakfastIncluded = True, 'SI', 'NO') AS  breakfastIncluded, IF(b.parking = True, 'SI', 'NO') AS parking, \r\n" +
    " IF(b.freeCancelation = True, 'SI', 'NO') AS freeCancelation,b.cost FROM room r,booking b WHERE b.room=r.idRoom AND b.guest= :guest", nativeQuery = true)
    List<BookingDTO> getBookings(Long guest);		
}
