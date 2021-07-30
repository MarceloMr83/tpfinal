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
	  String fromDate,@RequestParam(name = "to", required = false) String toDate,
	  @RequestParam(name = "sleeps", required = false) String occupancy,  Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			//convierte fecha ingresada en la vista como dd-mm-yyyy a yyyy-mm-dd
			Date from = dateFormat.parse(fromDate);
			Date to = dateFormat.parse(toDate);			
			List<Room> listRooms = roomService.retrieveAvailableRooms(from, to,occupancy);
			if(listRooms.size()==0)			{
				model.addAttribute("mensaje","No se encontraron habitaciones disponibles para esas fechas");
			}
			model.addAttribute("listRooms", listRooms);
			model.addAttribute("from", fromDate);
			model.addAttribute("to", toDate);
		} catch (ParseException e) {
			model.addAttribute("mensaje","Por favor ingrese las fechas de ingreso y egreso de la reserva");
		}
		return "availability";
	}


	
	
}

