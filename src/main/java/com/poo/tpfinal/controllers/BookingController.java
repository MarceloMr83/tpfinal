package com.poo.tpfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.poo.tpfinal.dto.BookingDetailDTO;
import com.poo.tpfinal.dto.UserBookingDTO;
import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.services.BookingService;

@Controller
@SessionAttributes("booking")
public class BookingController {

  @Autowired
	private BookingService bookingService;
    
	@GetMapping("/bookingDetail/{id}/{from}/{to}/{name}/{price}/{occupancy}/{facilities}")
  public String showConfirmScreen(@PathVariable("id") String idRoom,@PathVariable("from") String fromDate,
  @PathVariable("to") String toDate,@PathVariable("name") String name,@PathVariable("price") String price,
  @PathVariable("occupancy") String occupancy,@PathVariable("facilities") String facilities, Model model) {
    //agregar los datos a la vista previa de la reserva
    model.addAttribute("idRoom", idRoom);
    model.addAttribute("from", fromDate);
    model.addAttribute("to", toDate);
    model.addAttribute("name", name);
    //calcular costo de estadia
    float cost= bookingService.calculateCost(fromDate, toDate, price);
    model.addAttribute("cost",cost);
    model.addAttribute("occupancy", occupancy);
    model.addAttribute("facilities", facilities);
    return "booking-detail";
  }

  @GetMapping("/userBookings")
    public String bookings(Model model){   
      List<UserBookingDTO> listBooking = bookingService.getAllUserBookings();
      if(listBooking.size()==0){
        model.addAttribute("mensaje","Aun no se ha realizado ninguna reserva");
      }
      else{
      model.addAttribute("bookings",listBooking);
      }
      return "userBookings";
	}	

  @GetMapping("/userBookingDetail/{id}")
    public String userBookingDetail(@PathVariable("id") Long idBooking,Model model){   
      BookingDetailDTO booking = bookingService.getUserBookingsDetail(idBooking);
      model.addAttribute("booking",booking);
        return "userBookingDetail";
	}

  @PostMapping("/newBooking")
	public String newBooking(@RequestParam(name = "idRoom", required= true) Long idRoom,@RequestParam(name = "from", required= true)	String from,
	@RequestParam(name = "to", required= true) String to,@RequestParam(name = "total", required= false) float cost,@RequestParam(name = "parking", required= false)
	 boolean parking,@RequestParam(name = "breakfastIncluded", required= false) boolean breakfastIncluded,@RequestParam(name = "freeCancelation", required= false)
	 boolean freeCancelation){
		 //crea la instancia de booking y sigue a confirmar el pago
		 Booking booking = bookingService.newBooking(idRoom, from, to, cost, parking, breakfastIncluded, freeCancelation);
		 ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = request.getRequest().getSession(true);
    session.removeAttribute("booking");
		session.setAttribute("booking", booking); 
		return "payment";
	}








	
}

