package com.poo.tpfinal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Room {
    @Version
    private int version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    @Column(name = "idRoom")
    private long idRoom;
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "price", nullable = false)
    private float price;
    @NotNull
    @Column(name = "occupancy", nullable = false)
    private int occupancy;
    @NotNull
    @Column(name = "availability", nullable = false)
    private int availability;
    @NotEmpty
    @Column(name = "facilities", nullable = false)
    private String facilities;
    
    public void setVersionNum(int version){
		this.version=version;
	}
	
	public int getVersionNum(){
		 return version; 
		}
    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
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
		return "User [idRoom=" + idRoom + ", name=" + name + ", price=" + price + ", occupancy=" +
		 occupancy + ", availability=" + availability + ", facilities=" + facilities + "]";
	}

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
}
