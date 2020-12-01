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
	
	public List<Room> retrieveAvailableRooms(Date from, Date to) {
		return roomRepository.retrieveAvailableRooms(from, to);
	}
	
	
}
