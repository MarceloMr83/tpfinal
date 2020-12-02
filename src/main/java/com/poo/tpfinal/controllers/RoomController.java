package com.poo.tpfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


	@GetMapping("/availability")
    public String showBooking() {
        return "availability";
    }


	@PostMapping("/rooms")
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
		return "availability";

	}


	@GetMapping("/rooms/{id}")
	public Optional<Room> setBooking(@PathVariable Long id) {
		System.out.println("id room:"+id);
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


	
	

	
	
}

