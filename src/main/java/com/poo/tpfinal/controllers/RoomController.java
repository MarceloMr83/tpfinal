package com.poo.tpfinal.controllers;

<<<<<<< HEAD
=======
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

>>>>>>> ddf5ee29010728b058c5b6efd77b62b911bf475b
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

>>>>>>> ddf5ee29010728b058c5b6efd77b62b911bf475b
import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.services.RoomService;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomService;


<<<<<<< HEAD
	@GetMapping("/availability")
    public String showBooking() {
        return "availability";
    }


	@PostMapping("/rooms")
	 public String viewRooms(@RequestParam(name = "from", required= false)
	  String fromDate,@RequestParam(name = "to", required = false) String toDate,@RequestParam(name = "sleeps", required = false) String occupancy,  Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date from = dateFormat.parse(fromDate);
			Date to = dateFormat.parse(toDate);

			List<Room> listRooms = roomService.retrieveAvailableRooms(from, to,occupancy);
			model.addAttribute("listRooms", listRooms);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "availability";
	}


	@GetMapping("/rooms/{id}")
	public Optional<Room> setBooking(@PathVariable Long id) {
		 roomService.findById(id);
		 	Optional<Room> room = roomService.findById(id);
		 return room;
	}


/*	@RequestMapping("/rooms")
	 public String viewRooms(@RequestParam(name = "from", required= false)
	  String fromDate,@RequestParam(name = "to", required = false) String toDate, Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date from = dateFormat.parse(fromDate);
			Date to = dateFormat.parse(toDate);

			List<Room> listRooms = roomService.retrieveAvailableRooms(from, to);
			model.addAttribute("listRooms", listRooms);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "booking";

	}*/

/*	@GetMapping("/rooms/{id}")
	public Optional<Room> getUser(@PathVariable Long id) {
	    return roomService.findById(id);
	}*/


	
	

=======
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
>>>>>>> ddf5ee29010728b058c5b6efd77b62b911bf475b
	
	
}

