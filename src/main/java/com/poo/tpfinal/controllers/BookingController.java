package com.poo.tpfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpSession;
import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.entities.Payment;
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
   	   //Instancia de la nueva reserva
     Booking booking = bookingService.newBooking(idRoom, fromDate, toDate, price);
    //agregar los datos a la vista previa de la reserva
    model.addAttribute("idRoom", idRoom);
    model.addAttribute("from", fromDate);
    model.addAttribute("to", toDate);
    model.addAttribute("name", name);
    model.addAttribute("cost", booking.getCost());
    model.addAttribute("occupancy", occupancy);
    model.addAttribute("facilities", facilities);
	   ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	   HttpSession session = attributes.getRequest().getSession(true);  
	   session.setAttribute("booking", booking); 
  return "booking-detail";
}
/*
//guardar la reserva
  
	/*
	@GetMapping("/edit/{id}")
public String showUpdateForm(@PathVariable("id") long id, Model model) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    
    model.addAttribute("user", user);
    return "update-user";
}

@PostMapping("/update/{id}")
public String updateUser(@PathVariable("id") long id, @Valid User user, 
  BindingResult result, Model model) {
    if (result.hasErrors()) {
        user.setId(id);
        return "update-user";
    }
        
    userRepository.save(user);
    model.addAttribute("users", userRepository.findAll());
    return "redirect:/index";
}
    
@GetMapping("/delete/{id}")
public String deleteUser(@PathVariable("id") long id, Model model) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    userRepository.delete(user);
    model.addAttribute("users", userRepository.findAll());
    return "index";
	*/
	
}

