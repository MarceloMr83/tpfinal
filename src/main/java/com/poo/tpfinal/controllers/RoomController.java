package com.poo.tpfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.services.RoomService;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomService;

	@RequestMapping("/rooms")
	// public String viewRooms(@RequestParam(name = "from") String
	// fromDate,@RequestParam(name = "to") String toDate, Model model) {
	public String viewRooms(Model model) {
		// System.out.println(fromDate);
		// System.out.println(toDate);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date from = dateFormat.parse("2020-12-01");
			Date to = dateFormat.parse("2020-12-12");

			List<Room> listRooms = roomService.retrieveAvailableRooms(from, to);
			model.addAttribute("listRooms", listRooms);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "booking";

	}

	@GetMapping("/rooms/{id}")
	public Optional<Room> getUser(@PathVariable Long id) {
	    return roomService.findById(id);
	}

/*
	@GetMapping("/room")
	public List<Room> retrieveAvailableRooms() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date from = dateFormat.parse("2020-11-28");
			Date to = dateFormat.parse("2020-11-30");
			return roomService.retrieveAvailableRooms(from, to);
		} catch (ParseException e) {
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
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

