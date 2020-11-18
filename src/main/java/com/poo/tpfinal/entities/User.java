package com.poo.tpfinal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import java.util.Date;

@Entity
public class User {
	@Version
    private Long version;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idUser")
	private long idUser;
	@Column(name = "email", nullable = false, length = 150)
	private String email;
	@Column(name = "password", nullable = false, length = 50)
	private String password;
	@Column(name = "firstName", nullable = true, length = 50)
	private String firstName;
	@Column(name = "lastName", nullable = true, length = 50)
	private String lastName;
	@Column(name = "birthDate", nullable = true)
	private Date birthDate;
	@Column(name = "nationality", nullable = true, length = 50)
	private String nationality;
		
	public long getId() {
		return idUser;
	}
	public void setId(long idUser) {
		this.idUser = idUser;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "User [id=" + idUser + ", email=" + email + ", password=" + password + ", firstName=" +
		 firstName + ", lastName=" + lastName + "]";
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public User orElseThrow(Object object) {
		return null;
	}
		
}
