package com.poo.tpfinal.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.services.RoomService;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomService;


	@RequestMapping("/rooms")
    public String index(
        @RequestParam(value = "from", required = false) String fromDate,
        @RequestParam(value = "to", required = false) String toDate,
        Model model
    ) {
		System.out.println(fromDate + " "  + toDate);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date from = dateFormat.parse(fromDate);
			Date to=dateFormat.parse(toDate);
			List<Room> rooms = roomService.retrieveAvailableRooms(from, to);
			model.addAttribute("rooms", rooms);
		} catch (ParseException e) {
			model.addAttribute("error","Error");
			e.printStackTrace();
		}
		
        return "booking";
    }


	/*@GetMapping("/rooms/from/{from}/to/{to}")
	public String showFormForUpdate(@PathVariable(value = "from") String fromDate,
			@PathVariable(value = "to") String toDate, Model model) {
				System.out.println(fromDate + " "  + toDate);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date from = dateFormat.parse(fromDate);
			Date to=dateFormat.parse(toDate);
			List<Room> rooms = roomService.retrieveAvailableRooms(from, to);
			model.addAttribute("rooms", rooms);
		} catch (ParseException e) {
			model.addAttribute("error","Error");
			e.printStackTrace();
		}
		
        return "booking";
	}
	*/
	
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

