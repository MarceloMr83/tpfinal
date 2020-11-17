package com.poo.tpfinal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import java.util.Date;

@Entity
public class Cancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCancelation")
    private long id;
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;
    @OneToOne
    @JoinColumn(name = "idBooking", updatable = false, nullable = false)
    private Booking booking;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
		return "User [id=" + id + ", createdAt=" + createdAt.toString() + ", booking=" + booking.toString() +"]";
	}   

}
