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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.entities.User;
import com.poo.tpfinal.services.BookingService;
import com.poo.tpfinal.services.RoomService;

@Controller
public class BookingController {

  @Autowired
  private BookingService bookingService;

  @Autowired 
  private RoomService roomService;
  
  
  
	@GetMapping("/bookingDetail/{id}/{from}/{to}/{name}/{price}/{occupancy}/{facilities}")
public String showConfirmScreen(@PathVariable("id") Long idRoom,@PathVariable("from") String fromDate,
@PathVariable("to") String toDate,@PathVariable("name") String name,@PathVariable("price") String price,
@PathVariable("occupancy") String occupancy,@PathVariable("facilities") String facilities, Model model) {
    //User user = userRepository.findById(id)  .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    //Optional<Room> room = roomService.findById(id);
      
     // Booking booking = new Booking();
      //booking.setRoom(room.get());
     // System.out.println(room.get().getName());
    //  booking.setCheckIn(checkIn);
   //   booking.setCheckOut(checkOut);
   //   booking.setCreatedAt(createdAt);
      long daydiff=1;
      //Room room = roomService.findById("4");
      //System.out.println(room.getName());
      model.addAttribute("idRoom", idRoom);
      model.addAttribute("from", fromDate);
      model.addAttribute("to", toDate);
      model.addAttribute("name", name);

      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      try {
        Date from = dateFormat.parse(fromDate);
        Date to = dateFormat.parse(toDate);
        var diff = to.getTime() - from.getTime();   
        daydiff = diff / (1000 * 60 * 60 * 24);
        
    } 
    catch (ParseException e) {
			e.printStackTrace();
		}
      
    float cost = Float.parseFloat(price) * daydiff;          
  
      model.addAttribute("cost", cost);
      model.addAttribute("occupancy", occupancy);
      model.addAttribute("facilities", facilities);  
  return "booking-detail";
}

@PostMapping("/bookingConfirm") public String viewRooms(
  @RequestParam(name = "from", required= false) String fromDate,
  @RequestParam(name = "to", required = false) String toDate,
  @RequestParam(name = "idRoom", required = false) String idRoom, 
  @RequestParam(name = "cost", required = false) float cost, 
  Model model)  {
    //roomService.findById(idRoom);
   // List<Room> listRooms = roomService.retrieveAvailableRooms(from, to,occupancy);
    
      Room room = roomService.findById(idRoom);
      System.out.println(room.getName());
   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   try {
    Date checkIn = dateFormat.parse(fromDate);
    Date checkOut = dateFormat.parse(toDate);
    Date createdAt = new Date();

    //List<Room> listRooms = roomService.retrieveAvailableRooms(from, to,"2");
    //model.addAttribute("listRooms", listRooms);
    model.addAttribute("from", fromDate);
    model.addAttribute("to", toDate);

    //crea la instancia del objeto reserva
    Booking booking = new Booking();
    booking.setRoom(room);
    booking.setCheckIn(checkIn);
    booking.setCheckOut(checkOut);
    booking.setCreatedAt(createdAt);
    booking.setCost(cost);
    //obtiene el objeto user como instancia de usuario logueado
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User guest = (User) auth.getPrincipal();
    booking.setGuest(guest);

    bookingService.addBooking(booking);
    
  } catch (ParseException e) {
    e.printStackTrace();
  }
    return "availability";





    
    }



  /*
  @PostMapping("/bookingConfirm")
  public String viewRooms(@RequestParam(name = "from", required = false) String fromDate,
      @RequestParam(name = "to", required = false) String toDate,
      @RequestParam(name = "idRoom", required = false) Long idRoom, Model model) {


    Booking booking = new Booking();


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Room room = roomService.findByIdRoom(idRoom);

    Principal principal;

     model.addAttribute("room",room);
     model.addAttribute("from", fromDate);
     model.addAttribute("to", toDate);
     //model.addAttribute("principal", principal);

   return "booking-detail";
 }*/

  
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

