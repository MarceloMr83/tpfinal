package com.poo.tpfinal.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.entities.Payment;
import com.poo.tpfinal.repositories.PaymentRepository;
@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public void addPayment(Payment payment) {
	    paymentRepository.save(payment);
	}	

public Payment newPayment(String card,String cardNumber,Booking booking){
		Payment payment = new Payment();
		 Date createdAt = new Date(); 
		 payment.setCreatedAt(createdAt);
		 payment.setCard(card);
		 payment.setCardNumber(cardNumber);		 
		 payment.setBooking(booking);
		return payment;
	}
}
