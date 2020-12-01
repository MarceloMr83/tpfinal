package com.poo.tpfinal.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.poo.tpfinal.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
		
	/*@Query("select u from User u where u.username = :username")
	public Room findUserByUsername(@Param("username") String username);*/ 
		
	  @Query(value="SELECT * FROM room where NOT EXISTS (SELECT room FROM booking WHERE booking.room = room.idRoom AND \r\n" +
	  		"		((checkIn <= :from AND checkOut >= :from) OR (checkIn <= :to AND checkOut >= :to)))", nativeQuery = true)
	  List<Room> retrieveAvailableRooms(Date from, Date to);
      
}
