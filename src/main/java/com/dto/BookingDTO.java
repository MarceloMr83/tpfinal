package com.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//Crea un DTO con los datos de las reservas de un cliente
//La interfaz tiene métodos de acceso que
// coinciden con todas las propiedades de la consulta de selección que se está recuperando.
//proyecta los datos a la interfazx

public interface BookingDTO {

    Long getIdBooking();
    String getName();
    @DateTimeFormat(pattern = "dd-mm-yy")
    Date getCheckIn();   
    Date getCheckOut();   
    Date getCreatedAt();    
    String getBreakFastIncluded();    
    String getParking();  
    String getFreeCancelation();
    float getCost();    
}
