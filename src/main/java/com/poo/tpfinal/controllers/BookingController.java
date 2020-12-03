package com.poo.tpfinal.controllers;

//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.services.BookingService;
import com.poo.tpfinal.services.RoomService;

@Controller
public class BookingController {
  @Autowired
  private BookingService bookingService;
  private RoomService roomService;

  
	@GetMapping("/bookingDetail/{id}/{from}/{to}")
public String showUpdateForm(@PathVariable("id") long idRoom,@PathVariable("from") String fromDate,@PathVariable("to") String toDate, Model model) {
    //User user = userRepository.findById(id)  .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    //Optional<Room> room = roomService.findById(id);
    System.out.println(idRoom); 

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date checkIn;
    try {
      checkIn = dateFormat.parse(fromDate);
      Date checkOut = dateFormat.parse(toDate);
      Date createdAt = new Date();  
    //datos de habitacion    
    // Room room = roomService.findByIdRoom(idRoom);


   // Booking booking = new Booking();
   // booking.setRoom(room);
   // booking.setCheckIn(checkIn);
    //booking.setCheckOut(checkOut);
    //booking.setCreatedAt(createdAt);
   // model.addAttribute("booking", booking);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    /*model.addAttribute("id", id);
    model.addAttribute("from", from);
    model.addAttribute("to", to);
    model.addAttribute("room", room);*/
    
    
    return "booking-detail";
}


@PostMapping("/bookingConfirm") public String viewRooms(Model model) {


    return "booking-detail";

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

