package com.poo.tpfinal.repositories;

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
	List<Room> retrieveAvailableRooms(String from, String to, Long occupancy);*/

	//trae las habitaciones disponibles, si esta cancelada la reserva se toma como disponible
	@Query(value="select * FROM room where NOT EXISTS (SELECT room FROM booking WHERE booking.room = room.idRoom AND \r\n"+
	"((checkIn <= :from AND checkOut >= :from) OR \r\n " +
	"(checkIn <= :to AND checkOut >= :to)) \r\n"+
	"AND (SELECT room FROM booking b LEFT JOIN cancellation c ON c.booking=b.idBooking WHERE booking.idBooking = b.idBooking and c.idCancellation is null) \r\n"+
	") and	room.occupancy >= :occupancy ", nativeQuery = true)
	List<Room> retrieveAvailableRooms(String from, String to, Long occupancy);

		
	//verifica si una habitacion indicada esta ocupada en ese momento, si esta cancelada la reserva se toma como disponible
	@Query(value="select * FROM room where EXISTS (SELECT room FROM booking WHERE booking.room = room.idRoom AND \r\n"+
	"((checkIn <= :from AND checkOut >= :from) OR \r\n " +
	"(checkIn <= :to AND checkOut >= :to)) \r\n"+
	"AND (SELECT room FROM booking b LEFT JOIN cancellation c ON c.booking=b.idBooking WHERE booking.idBooking = b.idBooking and c.idCancellation is null) \r\n"+
	") and	room.idRoom = :idRoom ", nativeQuery = true)
	Room isRoomAvailable(String from, String to, Long idRoom);
	
}
