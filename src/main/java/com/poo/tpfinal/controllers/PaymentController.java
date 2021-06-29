package com.poo.tpfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.poo.tpfinal.entities.Payment;
import com.poo.tpfinal.services.PaymentService;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService paymentService;


	@GetMapping("/payment")
    public String showBooking() {
        return "payment";
    }

	/*@PostMapping("/rooms")
	 public String viewRooms(@RequestParam(name = "from", required= false)
	  String fromDate,@RequestParam(name = "to", required = false) String toDate,@RequestParam(name = "sleeps", required = false) String occupancy,  Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date from = dateFormat.parse(fromDate);
			Date to = dateFormat.parse(toDate);
			
			List<Room> listRooms = roomService.retrieveAvailableRooms(from, to,occupancy);
			model.addAttribute("listRooms", listRooms);
			model.addAttribute("from", fromDate);
			model.addAttribute("to", toDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "availability";
	}

/*
	@GetMapping("/rooms/{id}")
	public Optional<Room> setBooking(@PathVariable Long id) {
		 roomService.findById(id);
		 	Optional<Room> room = roomService.findById(id);
		 return room;
	}*/





	
	
}

