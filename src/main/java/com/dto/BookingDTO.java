package com.dto;

//Crea un DTO con los datos de las reservas de un cliente
//La interfaz tiene métodos de acceso que
// coinciden con todas las propiedades de la consulta de selección que se está recuperando.
//proyecta los datos a la interfazx

public interface BookingDTO {
    
    Long getIdBooking();
    String getName();
    String getCheckIn();   
    String getCheckOut();   
    String getCreatedAt();    
    String getBreakFastIncluded();    
    String getParking();  
    String getFreeCancelation();
    float getCost();    
}
