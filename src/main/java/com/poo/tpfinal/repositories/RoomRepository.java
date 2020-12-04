package com.poo.tpfinal.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.poo.tpfinal.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	public Optional<Room> findById(Long id);
		
	  @Query(value="SELECT * FROM room where NOT EXISTS (SELECT room FROM booking WHERE booking.room = room.idRoom AND \r\n" +
	  		"		((checkIn <= :from AND checkOut >= :from) OR (checkIn <= :to AND checkOut = :to))) and  room.occupancy = :occupancy", nativeQuery = true)
	  List<Room> retrieveAvailableRooms(Date from, Date to, String occupancy);
	  
	@Query(value="SELECT * FROM room where idRoom = :id", nativeQuery = true)
	  Room findByIdRoom(String id);
}
