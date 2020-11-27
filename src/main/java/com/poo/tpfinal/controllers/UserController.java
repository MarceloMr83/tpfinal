package com.poo.tpfinal.controllers;

//import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.poo.tpfinal.entities.User;
import com.poo.tpfinal.services.UserService;

@Controller

public class UserController {
    @Autowired
    private UserService userService;
    
    
    @GetMapping("/")
    public String getBack() {        
        return "index";
    }
    
    @GetMapping("/signup")
    public String createProjectForm(Model model) {        
         model.addAttribute("User", new User());
        return "signup";
    }
  
	@PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) { 
        model.addAttribute("User", new User());

        User existing = userService.getUserByEmail(user.getEmail());
    if (existing!=null) {
           result.rejectValue("email", null, "El usuario ya se encuentra registrado");    }   
        if (result.hasErrors()) {
            //model.addAttribute("error", result.getAllErrors());
            model.addAttribute("mensaje", result.getAllErrors());
            model.addAttribute("User", user);           
            return "signup";
        }	
        userService.addUser(user);
        model.addAttribute("users", userService.retrieveAllUsers());
        return "index";
    }        
        
        
      
    
/*@GetMapping("/delete/{id}")
public String deleteUser(@PathVariable("id") long id, Model model) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    userRepository.delete(user);
    model.addAttribute("users", userRepository.findAll());
    return "index";
	*/
	
}

