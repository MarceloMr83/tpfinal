package com.poo.tpfinal.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public String signUp(Model model) {        
         model.addAttribute("User", new User());
        return "signup";
    }
  
	@PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) { 
        //model.addAttribute("User", new User());
        User existing = userService.getUserByEmail(user.getEmail());
    if (existing!=null) {
           result.rejectValue("email", null, "El usuario ya se encuentra registrado"); 
         }   
        if (result.hasErrors()) {
            String detalleError="";
            List<FieldError> error = result.getFieldErrors();
            for(FieldError errors : error){
                detalleError=detalleError+" "+errors.getDefaultMessage()+".";

            }             
            model.addAttribute("mensaje",detalleError);
            model.addAttribute("User", user);           
            return "signup";
        }	
        userService.addUser(user);
        return "index";
    }         
   
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "El nombre de usuario o la contraseña no son validos");
        return "login";
    }

    @GetMapping("/logout")
    public void logout(Model model, String error, String logout) {
        SecurityContextHolder.clearContext();
        if (logout != null)
            model.addAttribute("message", "Sesion cerrada correctamente");
    }
	
}

