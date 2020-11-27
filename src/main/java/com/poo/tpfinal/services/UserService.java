package com.poo.tpfinal.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.poo.tpfinal.entities.User;
import com.poo.tpfinal.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	//encripta password de usuario antes de guardar
	public void addUser(User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
		userRepository.save(user);
	}

	public User getUser(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/*
	 * public User getUserLogin(String email,String password) { return
	 * userRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	 * }
	 */
	/*
	 * public User replaceUser(User user, Long id) { return
	 * userRepository.findById(id) .map(u -> { u.setEmail(user.getEmail());
	 * u.setPassword(user.getPassword()); u.setFirstName(user.getFirstName());
	 * u.setLastName(user.getLastName()); return userRepository.save(u); })
	 * .orElseGet(() -> { return userRepository.save(user); }); }
	 */

	public void deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email);
	}
	
/*	public User findUserByUsername(String username) {
		//return userRepository.findUserByUsername(username);
	}*/
	
}
