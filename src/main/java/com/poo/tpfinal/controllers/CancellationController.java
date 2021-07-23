package com.poo.tpfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

import com.dto.BookingDTO;
import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.services.BookingService;

@Controller
@SessionAttributes("booking")
public class CancellationController {

  @Autowired
	private BookingService bookingService;
    
	@GetMapping("/cancelBooking/{id}")
  public String showConfirmScreen(@PathVariable("id") Long idBooking, Model model) {
    //agregar los datos a la vista previa de la reserva   
    
    return "booking-detail";
  }

  @GetMapping("/cancellationConfirm")
    public String bookings(Model model){   

     // model.addAttribute("bookings",listBooking);
        return "Cancellationdetail";
	}	


	
}

