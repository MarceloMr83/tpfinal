package com.poo.tpfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.entities.Payment;
import com.poo.tpfinal.services.BookingService;
import com.poo.tpfinal.services.PaymentService;
import com.poo.tpfinal.services.RoomService;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private RoomService roomService;

	@PostMapping("/paymentConfirm")
    public String paymentConfirm(@RequestParam(name = "card", required= false)
	String card,@RequestParam(name = "cardNumber", required = false) String cardNumber,  Model model){    
		 ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		 //trae el booking creado en el otro controlador
		 HttpSession session = request.getRequest().getSession(true);
		 Booking booking = (Booking) session.getAttribute("booking");
		 System.out.println("LLEGO EL COSTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT:"+booking.getCost());

		 //si no se ingreso el numreo de tarjeta o el tipo, los vuelve a pedir
		 if(card.equals("") || cardNumber.equals("")){
			model.addAttribute("mensaje","Ingrese los datos de la tarjeta");
			return "payment";
		 }
		 //crea la instancia de payment,
		 Payment payment = paymentService.newPayment(card, cardNumber, booking);	
		 //verificar si tardo mucho tiempo para realizar el pago y alguien ya la reservo

		 Date from = booking.getCheckIn();
		 Date to = booking.getCheckOut();

		//convertimos el date a formato yyyy-mm-dd para que coincida con el formato date de la base de datos
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String fromAvailable = formatter.format(from);
		String toAvailable = formatter.format(to);	
		Long idRoom = booking.getRoom().getIdRoom();
		 //verificar si ya la reservo alguien y ya no esta disponible
		if(roomService.isRoomAvailable(fromAvailable, toAvailable, idRoom)){
			//guarda la reserva y el pago
			bookingService.addBooking(booking);
			paymentService.addPayment(payment);	
			model.addAttribute("mensaje", "La reserva se realizó correctamente");	
		}
		else{
			model.addAttribute("mensaje","La habitacion ya no se encuentra disponible");
		}
		//limpiamos el objeto reserva de la sesion
		session.removeAttribute("booking");		
        return "status";
	}	
}
