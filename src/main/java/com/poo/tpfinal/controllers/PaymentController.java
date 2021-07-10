package com.poo.tpfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

import javax.servlet.http.HttpSession;
import com.poo.tpfinal.entities.Payment;
import com.poo.tpfinal.services.PaymentService;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

/*
	@GetMapping("/payment")
    public String showBooking() {
        return "payment";
    }*/

	@PostMapping("/payment")
    public String payment(Payment payment,@RequestParam(name = "card", required= false)
	String card,@RequestParam(name = "cardNumber", required = false) String cardNumber,  Model model) { {    
		//pasa el objeto payment al html    
         model.addAttribute("Payment", new Payment());
		 ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		 HttpSession session = attributes.getRequest().getSession(true);
		 Date createdAt = new Date(); 
		 payment.setCreatedAt(createdAt);
		 session.setAttribute("payment", payment);
        return "/bookingConfirm";
    }

	
	
	}	
}

