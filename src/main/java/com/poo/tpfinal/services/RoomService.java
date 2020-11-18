package com.poo.tpfinal.services;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.repositories.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;

	public List<Room> retrieveAllRooms() {
		return roomRepository.findAll();
	}
	
	public void addRoom(Room room) {
	    roomRepository.save(room);
	}
	
	public Room getRoom(Long id) {
	    return roomRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}
	
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

	public void deleteRoom(@PathVariable Long id) {
		roomRepository.deleteById(id);
	}
	
/*	public User findUserByUsername(String username) {
		//return userRepository.findUserByUsername(username);
	}*/
	
}
