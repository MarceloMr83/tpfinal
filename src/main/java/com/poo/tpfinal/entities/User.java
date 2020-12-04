package com.poo.tpfinal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Entity
public class User implements UserDetails {
	/**
	 *
	 */
	@Version
	private int version;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduser")
	private long idUser;

	@Valid
	@Email
	@NotEmpty(message = "{El email debe completarse}")
	@Column(name = "email", nullable = false, length = 150)
	private String email;
	@Valid
	@NotEmpty(message = "{El password debe completarse}")
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	@Valid
	@NotEmpty(message = "{El nombre debe completarse}")
	@Column(name = "firstname", nullable = false, length = 45)
	private String firstName;
	@Valid
	@NotEmpty(message = "{El apellido debe completarse}")
	@Column(name = "lastname", nullable = false, length = 45)
	private String lastName;
	@Valid
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	@Column(name = "birthdate", nullable = false)
	private Date birthDate;
	@Valid
	@Column(name = "nationality", nullable = false, length = 45)
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
		return "User [id=" + idUser + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public void setVersionNum(int version){
		this.version=version;
	}
	
	public int getVersionNum(){
		 return version; 
		}
		
}
