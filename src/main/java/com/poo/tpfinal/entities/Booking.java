package com.poo.tpfinal.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

@Entity
public class Booking {
    @Version
    private Long version;
    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idBooking")
    private long idBooking;
    @NotEmpty
    @OneToOne
    @JoinColumn(name = "idUser", updatable = false, nullable = false)
    private User guest;
    @NotEmpty
    @Column(name = "checkIn", nullable = false)
    private Date checkIn;
    @NotEmpty
    @Column(name = "checkOut", nullable = false)
    private Date checkOut;
    @NotEmpty
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;
    @NotEmpty
   @ManyToOne
   @JoinColumn(name = "idRoom", updatable = false, nullable = false)
    private Room room; 
    @NotEmpty     
    @Column(name = "breakfastIncluded", nullable = false)
    private boolean breakfastIncluded;
    @NotEmpty
    @Column(name = "parking", nullable = false)
    private boolean parking;
    @NotEmpty
    @Column(name = "freeCancelation", nullable = false)
    private boolean freeCancelation;
    @NotEmpty
    @Column(name = "cost", nullable = false)
    private float cost;
    
    //agregar @Version
    public Booking(){
            super();
          //  listRoom=new ArrayList<Room>();
          }
    

    public long getId() {
        return idBooking;
    }

    public void setId(long idBooking) {
        this.idBooking = idBooking;
    }

    public User getGuest() {
        return guest;
    }

    public void setGuest(User guest) {
        this.guest = guest;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(boolean breackfastIncluded) {
        this.breakfastIncluded = breackfastIncluded;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isFreeCancelation() {
        return freeCancelation;
    }

    public void setFreeCancelation(boolean freeCancelation) {
        this.freeCancelation = freeCancelation;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
	public String toString() {
		return "User [id=" + idBooking + ", guest=" + guest + ", checkIn=" + checkIn + ", checkOut=" +
         checkOut + ", createdAt=" + createdAt + ", room=" + room.getName() + ", breackfastIncluded=" + breakfastIncluded + 
         ", parking=" + parking + ", freeCancelation=" + freeCancelation + ", cost=" + cost +"]";
	}  
    
}
