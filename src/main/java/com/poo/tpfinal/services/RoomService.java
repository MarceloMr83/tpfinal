package com.poo.tpfinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.repositories.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public List<Room> retrieveAvailableRooms(String from, String to,Long occupancy) {
		return roomRepository.retrieveAvailableRooms(from, to, occupancy);
	}

	public Boolean isRoomAvailable(String from, String to,Long idRoom) {
		Room room = roomRepository.isRoomAvailable(from, to, idRoom);
		if(room == null){
			return true;
		}
		else{
			return false;
		}
	}

	public Room findById(Long id) {
		return roomRepository.getOne(id);
	}
	
}
