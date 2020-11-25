package com.poo.tpfinal.controllers;

//import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import com.poo.tpfinal.entities.User;
import com.poo.tpfinal.services.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService userService;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String createProjectForm(Model model) {        
         model.addAttribute("User", new User());
        return "signup";
    }
  
	@PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) { 
        model.addAttribute("User", new User());
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "signup";
        }	
      /*  if(userService.getUserByEmail(user.getEmail())){
            return "signup";
            
        }*/

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        		userService.addUser(user);
        model.addAttribute("users", userService.retrieveAllUsers());
        return "index";
    }
   


/*	@GetMapping("/edit/{id}")
public String showUpdateForm(@PathVariable("id") long id, Model model) {
    User user = userService.findById(id)
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
