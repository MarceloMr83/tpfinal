package com.poo.tpfinal.services;

import java.util.Date;
import java.util.List;
<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> ddf5ee29010728b058c5b6efd77b62b911bf475b
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.repositories.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

<<<<<<< HEAD
	public List<Room> retrieveAvailableRooms(Date from, Date to,String occupancy) {
		return roomRepository.retrieveAvailableRooms(from, to, occupancy);
=======
	public List<Room> retrieveAvailableRooms(Date from, Date to) {
		return roomRepository.retrieveAvailableRooms(from, to);
	}


	
/*
	public List<Room> retrieveAllRooms() {
		return roomRepository.findAll();
>>>>>>> ddf5ee29010728b058c5b6efd77b62b911bf475b
	}
	
	public Optional<Room> findById(long id) {
		return roomRepository.findById(id);
	}

<<<<<<< HEAD
	/*public Room findRoom(Long id){
		return roomRepository.
=======
	public void addRoom(Room room) {
	    roomRepository.save(room);
	}
	
	public Room getRoom(Long id) {
	    return roomRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}*/
	
	/*
	public Room replaceRoom(Room room, Long id) {
		return roomRepository.findById(id)
	      .map(u -> {
	        u.setName(room.getName());
	        u.setPrice(room.getPrice());
	        u.setOccupancy(room.getOccupancy());
	        u.setAvailability(room.getAvailability());
	        return roomRepository.save(u);
	      })
	      .orElseGet(() -> {
	        return roomRepository.save(room);
	      });
	}
*/
/*
	public void deleteRoom(@PathVariable Long id) {
		roomRepository.deleteById(id);
>>>>>>> ddf5ee29010728b058c5b6efd77b62b911bf475b
	}*/
	
	
}
