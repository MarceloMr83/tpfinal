package com.poo.tpfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import com.poo.tpfinal.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

    @Query(value="SELECT * FROM room where NOT EXISTS (SELECT room_id FROM booking WHERE booking.room_id = room.id AND \r\n" + 
    "		((check_in <= :from AND check_out >= :from) OR (check_in <= :to AND check_out >= :to)))", nativeQuery = true)
List<Room> retrieveAvailableRooms(Date from, Date to);

/*
    @Modifying
    @Transactional
    @Query("update Room r set r.name = :name, r.price = :price, r.occupancy = :occupancy, r.availability = :availability, r.facilities = :facilities")
    void updateRoom(@Param("name") String name, 
    @Param("price") Double price, @Param("occupancy") Integer occupancy, @Param("availability") Boolean availability, @Param("facilities") String facilities);*/ 
    //@Query("select c from City c where c.name like %?1")

   /* @Query("select r from Room r where r.occupancy >= :occupancy and  r.availability > (Select count(b) from Booking b where b.room = r and b.checkIn between :checkIn and :checkOut)")
    public List<Room> findRoomsAvailability(@Param("occupancy")int occupancy,@Param("checkIn") Date checkIn,@Param("checkOut") Date checkOut);
    
    
    
    //@Query("Select r From Room r where r.occupancy >= :occupancy and  r.availability > (Select count(b) From Booking b where b.room = r and b.checkIn between :checkIn and :checkOut");
   /* @Query("select c from City c where c.name like %?1")
    List<City> findByNameEndsWith(String chars);*/

		
}
