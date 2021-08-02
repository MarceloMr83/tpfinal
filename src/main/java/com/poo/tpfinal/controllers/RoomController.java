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
	  @RequestParam(name = "sleeps", required = false) Long occupancy,  Model model) {
		//convierte fecha ingresada a yyyy-mm-dd	
		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		Date from = null;
		Date to = null;
		String fromBooking=null;
		String toBooking=null;

		try {
			from = parseador.parse(fromDate);
			to = parseador.parse(toDate);
				 fromBooking= formateador.format(from);
				 toBooking= formateador.format(to);
				//busca las habitaciones disponibles en esas fechas, para ela cantidad de ocupantes indicada
				//si la lista esta vacia, no hay habitaciones disponibles
				 List<Room> listRooms = roomService.retrieveAvailableRooms(fromBooking, toBooking,occupancy);
				 if(listRooms.size()==0){
					model.addAttribute("mensaje","No se encontraron habitaciones disponibles");
				}
				else{
					model.addAttribute("listRooms", listRooms);
				 	model.addAttribute("from", fromDate);
				 	model.addAttribute("to", toDate);
				}


			} catch (ParseException e) {
				model.addAttribute("mensaje","Por favor ingrese las fechas de ingreso y egreso de la reserva");
				e.printStackTrace();
			}	
		return "availability";
		
	}





	
	
}

