package com.poo.tpfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

import com.poo.tpfinal.dto.BookingDTO;
import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.services.BookingService;
import com.poo.tpfinal.services.CancellationService;

@Controller
@SessionAttributes("booking")
public class CancellationController {

  @Autowired
	private BookingService bookingService;

  @Autowired
	private CancellationService cancellationService;


    
	@GetMapping("/cancelBooking/{id}")
  public String showConfirmScreen(@PathVariable("id") Long idBooking, Model model) {
    Booking booking = bookingService.getBooking(idBooking);
    if(cancellationService.cancellationIsAvailable(booking)){
      return "cancelBooking";
    }
    model.addAttribute("mensaje","Solo se pueden cancelar reservas hasta 48Hs antes de su inicio");
    return "reserve-detail";
  }

  @GetMapping("/cancellationConfirm")
    public String bookings(Model model){   

     // model.addAttribute("bookings",listBooking);
        return "Cancellationdetail";
	}	


	
}

