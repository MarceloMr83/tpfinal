package com.poo.tpfinal.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.tpfinal.entities.Payment;
import com.poo.tpfinal.entities.Room;
import com.poo.tpfinal.repositories.PaymentRepository;
import com.poo.tpfinal.repositories.RoomRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	/*public List<Payment> retrieveAvailableRooms(Date from, Date to,String occupancy) {
		return paymentRepository.retrieveAvailableRooms(from, to, occupancy);
	}
*/

/*
	public Room findById(String id) {
	//	return roomRepository.findByIdRoom(id);
	}
	*/
}
