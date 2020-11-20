package com.poo.tpfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poo.tpfinal.entities.Cancellation;

@Repository
public interface CancellationRepository extends JpaRepository<Cancellation, Long>{

		
}
