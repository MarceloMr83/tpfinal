package com.poo.tpfinal.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	
	public Room findByIdRoom(long idRoom) {
		return roomRepository.findByIdRoom(idRoom);
	}

	/*public Room findRoom(Long id){
		return roomRepository.
	}*/
	
	
}
