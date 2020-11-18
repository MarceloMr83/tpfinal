package com.poo.tpfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poo.tpfinal.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

		
}
