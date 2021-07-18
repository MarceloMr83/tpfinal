package com.poo.tpfinal.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date date;

    public Date getDate(String date){
	   try { 
		 this.date = dateFormat.parse(date);
	   } 
	   catch (ParseException e) {
			   e.printStackTrace();
		   }
           return this.date;
       }
       

}
    

