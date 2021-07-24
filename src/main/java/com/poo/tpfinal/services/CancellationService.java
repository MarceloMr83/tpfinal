package com.poo.tpfinal.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.entities.Cancellation;
import com.poo.tpfinal.entities.Payment;
import com.poo.tpfinal.repositories.BookingRepository;
import com.poo.tpfinal.repositories.CancellationRepository;
import com.poo.tpfinal.repositories.PaymentRepository;
@Service
public class CancellationService {

    @Autowired
	private CancellationRepository cancellationRepository;


	public void addCancellation(Booking booking) {
        Cancellation cancellation = new Cancellation();
        Date createdAt = new Date(); 
        cancellation.setCreatedAt(createdAt);
        cancellation.setBooking(booking);
	    cancellationRepository.save(cancellation);
	}	

    public Boolean cancellationIsAvailable(Booking booking) {
        Date now = new Date(); 
        Date from = booking.getCheckIn();
        var diff = from.getTime() - now.getTime(); 
        if(diff <=2){
            return false;
        }
        else{
            return true;
        }
	}	


    /*

public Payment newPayment(String card,String cardNumber,Booking booking){
		Payment payment = new Payment();
		 Date createdAt = new Date(); 
		 payment.setCreatedAt(createdAt);
		 payment.setCard(card);
		 payment.setCardNumber(cardNumber);		 
		 payment.setBooking(booking);
		return payment;
	}*/

}
