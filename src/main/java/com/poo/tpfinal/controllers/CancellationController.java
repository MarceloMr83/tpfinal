package com.poo.tpfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.poo.tpfinal.dto.BookingDetailDTO;
import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.services.BookingService;
import com.poo.tpfinal.services.CancellationService;
import org.springframework.web.context.request.RequestContextHolder;

@Controller
public class CancellationController {

  @Autowired
	private BookingService bookingService;

  @Autowired
	private CancellationService cancellationService;
    
	@PostMapping("/cancelBooking")
  public String cancelBooking(@RequestParam(name = "idBooking", required= false) Long idBooking, Model model) {
    Booking booking = bookingService.getBooking(idBooking);
    if(cancellationService.cancellationIsAvailable(booking)){
      //pasamos el objeto booking a sesion
      ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = request.getRequest().getSession(true);
      session.setAttribute("booking", booking); 
        return "cancelBooking";
    }
    model.addAttribute("mensaje","Solo se pueden cancelar reservas hasta 48Hs antes de su inicio");
    return "userBookingDetail";
  }

  @PostMapping("/cancellationConfirm")
    public String bookings(Model model){
      ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = request.getRequest().getSession(true);
		 Booking booking = (Booking) session.getAttribute("booking"); 
     try {
      cancellationService.addCancellation(booking);
      model.addAttribute("mensaje","Su reserva fue cancelada!");       
     } catch (Exception e) {
        model.addAttribute("mensaje",e);
     }  
     
        return "status";
	}	


	
}

