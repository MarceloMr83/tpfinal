package com.poo.tpfinal.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.repositories.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public List<Room> retrieveAvailableRooms(Date from, Date to,String occupancy) {
		return roomRepository.retrieveAvailableRooms(from, to, occupancy);
	}

	public Boolean isRoomAvailable(Date from, Date to,Long idRoom) {
		System.out.println("habitacion"+idRoom);
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
