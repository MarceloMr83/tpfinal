package com.poo.tpfinal.controllers;

//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.poo.tpfinal.services.BookingService;

@Controller
public class BookingController {
	@Autowired
	private BookingService bookingService;

	 


    @GetMapping("/bookingDetail")
    public String showBookingDetail() {
        return "booking-detail";
    }
    
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

