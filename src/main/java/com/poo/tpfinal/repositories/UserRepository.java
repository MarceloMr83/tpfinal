package com.poo.tpfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poo.tpfinal.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);
 
	//el usuario existe o no
/*	@Query("select u from User u where u.email = :email")
	public Boolean findUserByEmail(@Param("email") String email); 
	//verificar si coincide ususario y contraseña	
	@Query("select u from User u where u.email = :email and u.password =:password")
	public User verifiyLogin(@Param("email") String email, @Param("password") String password); */


}
