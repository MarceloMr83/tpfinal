package com.poo.tpfinal.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poo.tpfinal.entities.Booking;
import com.poo.tpfinal.entities.Cancellation;
import com.poo.tpfinal.repositories.CancellationRepository;
@Service
public class CancellationService {

    @Autowired
	private CancellationRepository cancellationRepository;

    //crea y persiste el objeto cancellation en la base de datos
	public void addCancellation(Booking booking) {
        Cancellation cancellation = new Cancellation();
        Date createdAt = new Date(); 
        cancellation.setCreatedAt(createdAt);
        cancellation.setBooking(booking);
	    cancellationRepository.save(cancellation);
	}	

    //si hay menos de 2 dias antes de la reserva, ya no se puede cancelar
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
}
