package com.poo.tpfinal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import java.util.Date;

@Entity
public class Cancellation {
    @Version
    private int version;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCancelation")
    private long idCancelation;
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;
    @NotNull
    @OneToOne
    @JoinColumn(name = "idBooking", updatable = false, nullable = false)
    private Booking booking;

    public void setVersionNum(int version){
		this.version=version;
	}
	
	public int getVersionNum(){
		 return version; 
		}
    
    public long getId() {
        return idCancelation;
    }

    public void setId(long idCancelation) {
        this.idCancelation = idCancelation;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    @Override
	public String toString() {
		return "User [id=" + idCancelation + ", createdAt=" + createdAt.toString() + ", booking=" + booking.toString() +"]";
	}   

}
