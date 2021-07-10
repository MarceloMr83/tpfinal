package com.poo.tpfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.entities.Payment;
import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.entities.User;
import com.poo.tpfinal.services.BookingService;
import com.poo.tpfinal.services.RoomService;

@Controller
@SessionAttributes("booking")
public class BookingController {

  @Autowired
  private BookingService bookingService;

  @Autowired 
  private RoomService roomService;
    
	@GetMapping("/bookingDetail/{id}/{from}/{to}/{name}/{price}/{occupancy}/{facilities}")
  public String showConfirmScreen(@PathVariable("id") String idRoom,@PathVariable("from") String fromDate,
  @PathVariable("to") String toDate,@PathVariable("name") String name,@PathVariable("price") String price,
  @PathVariable("occupancy") String occupancy,@PathVariable("facilities") String facilities, Model model) {
      long daydiff=1;
       //crea la instancia del objeto reserva
       Booking booking = new Booking();

       //agregar los datos a la vista previa de la reserva
      model.addAttribute("idRoom", idRoom);
      model.addAttribute("from", fromDate);
      model.addAttribute("to", toDate);
      model.addAttribute("name", name);

      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
      //formatea las fecha desde y hasta y la agrega la reserva
      try {
        Date createdAt = new Date(); 
        booking.setCreatedAt(createdAt);   
        Date from = dateFormat.parse(fromDate);
        Date to = dateFormat.parse(toDate);
        var diff = to.getTime() - from.getTime();   
        daydiff = diff / (1000 * 60 * 60 * 24);

        //atributos del objeto reserva
        booking.setCheckIn(from);
        booking.setCheckOut(to);

        //mostrar en la vista previa 
        model.addAttribute("from", fromDate);
        model.addAttribute("to", toDate);        
      } 
      catch (ParseException e) {
			  e.printStackTrace();
		  }        
      
      float cost = Float.parseFloat(price) * daydiff; 

      //mostrar en la vista previa 
      model.addAttribute("cost", cost);
      model.addAttribute("occupancy", occupancy);
      model.addAttribute("facilities", facilities);

      Room room = roomService.findById(idRoom);

      //obtiene el objeto user como instancia de usuario logueado
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User guest = (User) auth.getPrincipal();
    
      booking.setGuest(guest);
      booking.setRoom(room);
      booking.setCost(cost);

      //agrega en la sesion el objeto booking
      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = attributes.getRequest().getSession(true);  
      session.setAttribute("booking", booking);    
  return "booking-detail";
}

//guardar la reserva
@PostMapping("/bookingConfirm") public String saveBooking(Model model)  {
    //session para pasar informacion entre controladores
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = attributes.getRequest().getSession(true);
      //cast a objeto booking 
      Booking booking =  (Booking) session.getAttribute("booking");
      Payment payment = (Payment) session.getAttribute("payment");      
      bookingService.addBooking(booking);   
      return "availability";    
  }
  

  /*
  
  @PostMapping("/bookingConfirm")
  public String viewRooms(Model model) {
    Booking booking = new Booking();
   // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   // Room room = roomService.findByIdRoom(idRoom);
  // Principal principal;

     model.addAttribute("room",room);
     model.addAttribute("from", fromDate);
     model.addAttribute("to", toDate);
     //model.addAttribute("principal", principal);
 

   return "booking-detail";
 }
*/
  
  /*
  
  
  @GetMapping("/bookingDetail")
    public String showBookingDetail() {
        return "booking-detail";
    }
    */
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

