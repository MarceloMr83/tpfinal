package com.poo.tpfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.poo.tpfinal.dto.BookingDetailDTO;
import com.poo.tpfinal.dto.UserBookingDTO;
import com.poo.tpfinal.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

    /*@Query(value="SELECT b.idBooking,DATE_FORMAT(b.createdAt,'%d-%m-%Y') as createdAt, DATE_FORMAT(b.checkIn,'%d-%m-%Y') as checkIn,  \r\n" +
     "DATE_FORMAT(b.checkOut,'%d-%m-%Y') as checkOut \r\n" +
     "from booking b WHERE b.guest= :guest order by b.checkIn asc", nativeQuery = true)
    List<UserBookingDTO> getUserBookings(Long guest);*/
    
    //trae las reservas del usuario si no fueron canceldas
    @Query(value="SELECT b.idBooking,DATE_FORMAT(b.createdAt,'%d-%m-%Y') as createdAt, DATE_FORMAT(b.checkIn,'%d-%m-%Y') as checkIn, \r\n"+
    "DATE_FORMAT(b.checkOut,'%d-%m-%Y') as checkOut \r\n"+
    "from booking b WHERE b.guest= :guest \r\n"+
    "AND not EXISTS (select booking FROM cancellation WHERE booking=b.idBooking) \r\n"+
     "order by b.checkIn asc", nativeQuery = true)
    List<UserBookingDTO> getUserBookings(Long guest);
    
    
    @Query(value="SELECT b.idBooking,r.name,r.facilities,r.occupancy,DATE_FORMAT(b.checkIn,'%d-%m-%Y') as checkIn,  \r\n" +
    "DATE_FORMAT(b.checkOut,'%d-%m-%Y') as checkOut,DATE_FORMAT(b.createdAt,'%d-%m-%Y') as createdAt, \r\n" +
    " IF(b.breakfastIncluded = True, 'SI', 'NO') AS  breakfastIncluded, IF(b.parking = True, 'SI', 'NO') AS parking, \r\n" +
   " IF(b.freeCancelation = True, 'SI', 'NO') AS freeCancelation,b.cost FROM room r,booking b WHERE b.room=r.idRoom AND b.idBooking= :idBooking", nativeQuery = true)
    BookingDetailDTO getUserBookingDetail(Long idBooking);
}
