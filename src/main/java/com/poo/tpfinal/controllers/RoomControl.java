package com.poo.tpfinal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.services.RoomService;


@Controller
public class RoomControl {
	
	@Autowired
	private RoomService roomService;

	
	
	/*
	@PostMapping("/roomsAvailabilities")
	public void addUser(@RequestBody Room room) {
		roomService.addRoom(room);
	}
	
	@GetMapping("/rooms/{id}")
	public Room getUser(@PathVariable Long id) {
	    return roomService.getRoom(id);
	}
	
	@PutMapping("/rooms/{id}")
	public Room replaceUser(@RequestBody Room room, @PathVariable Long id) {
	    return roomService.replaceRoom(room, id);
	}

	@DeleteMapping("/rooms/{id}")
	public void deleteRoom(@PathVariable Long id) {
		  roomService.deleteRoom(id);
	}
	*/
	
	
}

