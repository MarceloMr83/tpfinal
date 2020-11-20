package com.poo.tpfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poo.tpfinal.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

		
}
