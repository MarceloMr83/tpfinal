package com.poo.tpfinal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import java.util.Date;

@Entity
public class Payment {
    @Version
    private int version;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPayment")
    private long idPayment;
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;
    @NotNull
    @OneToOne
    @JoinColumn(name = "idBooking", updatable = false, nullable = false)
    private Booking booking;
    @NotEmpty
    @Column(name = "card", nullable = false)
    private String card;
    @NotEmpty
    @Column(name = "cardNumber", nullable = false)
    private String cardNumber;
    

    public void setVersionNum(int version){
		this.version=version;
	}
	
	public int getVersionNum(){
		 return version; 
		}

    public long getId() {
        return idPayment;
    }

    public void setId(long idPayment) {
        this.idPayment = idPayment;
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

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
	public String toString() {
		return "User [id=" + idPayment + ", createdAt=" + createdAt.toString() + ", booking=" + booking.toString() + ", card=" +
         card + ", cardNumber=" + cardNumber +"]";
	}     
}
