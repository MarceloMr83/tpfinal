package com.poo.tpfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
/*import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;*/
import org.springframework.stereotype.Repository;

import com.poo.tpfinal.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
/*
	@Query("select u from user u where u.email = :email")
	public User findUserByEmail(@Param("email") String email); */
		
}
