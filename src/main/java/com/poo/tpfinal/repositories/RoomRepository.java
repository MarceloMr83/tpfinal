package com.poo.tpfinal.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.poo.tpfinal.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	public Optional<Room> findById(Long id);

	//query original, solo habitacionesa disponibles
	/*@Query(value="SELECT * FROM room where NOT EXISTS (SELECT room FROM booking WHERE booking.room = room.idRoom AND \r\n" +
	  		"		((checkIn <= :from AND checkOut >= :from) OR (checkIn <= :to AND checkOut = :to))) and  room.occupancy >= :occupancy", nativeQuery = true)
	List<Room> retrieveAvailableRooms(Date from, Date to, String occupancy);*/

	//trae las habitaciones disponibles, si esta cancelada la reserva se toma como disponible
	@Query(value="SELECT * FROM room where NOT EXISTS (SELECT room FROM booking WHERE booking.room = room.idRoom AND \r\n" +
	"((checkIn <= :from AND checkOut >= :from) OR \r\n"+
	"(checkIn <= :to AND checkOut >= :to))) and \r\n" +
	"room.occupancy >= :occupancy \r\n"+
	"or exists \r\n"+
	"(SELECT idBooking FROM booking,cancellation WHERE \r\n" +
	"((checkIn <= :from AND checkOut >= :from) OR \r\n"+
	"(checkIn <= :to AND checkOut >= :to)) AND cancellation.booking IS not NULL AND room.occupancy >= :occupancy)", nativeQuery = true)
	List<Room> retrieveAvailableRooms(Date from, Date to, String occupancy);
		
	//verifica si una habitacion indicada esta ocupada en ese momento, si esta cancelada la reserva se toma como disponible
	@Query(value="SELECT * FROM room where EXISTS (SELECT room FROM booking WHERE booking.room = room.idRoom AND \r\n"+
	"((checkIn <= :from AND checkOut >= :from) OR \r\n"+
	"(checkIn <= :to AND checkOut >= :to)) and room.idRoom = :idRoom) and \r\n"+
	"not exists (SELECT idBooking FROM booking,cancellation WHERE \r\n"+
	"((checkIn <= :from AND checkOut >= :from) OR \r\n"+
	"(checkIn <= :to AND checkOut >= :to)) AND cancellation.booking IS not NULL AND room.idRoom = :idRoom)", nativeQuery = true)
	Room isRoomAvailable(String from, String to, Long idRoom);
	

/*
	@Query(value="SELECT * FROM room where EXISTS (SELECT room FROM booking WHERE booking.room = room.idRoom AND \r\n" +
	" ((checkIn <= :from AND checkOut >= :from) OR (checkIn <= :to AND checkOut >= :to)) and room.idRoom = :idRoom)", nativeQuery = true)
	Room isRoomAvailable(String from, String to, Long idRoom);*/
}
