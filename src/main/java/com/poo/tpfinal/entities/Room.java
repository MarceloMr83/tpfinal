package com.poo.tpfinal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idRoom")
    private long id;
    @Column(name = "name", nullable = true, length = 50)
    private String name;
    @Column(name = "price", nullable = true, length = 10)
    private float price;
    @Column(name = "occupancy", nullable = false, length = 10)
    private int occupancy;
    @Column(name = "availabilty", nullable = false, length = 1)
    private int availability;
    @Column(name = "facilities", nullable = true, length = 50)
    private String facilities;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    @Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", price=" + price + ", occupancy=" +
		 occupancy + ", availability=" + availability + ", facilities=" + facilities + "]";
	}   

    
}
