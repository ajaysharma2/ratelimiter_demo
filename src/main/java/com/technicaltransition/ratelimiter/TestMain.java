package com.technicaltransition.ratelimiter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        // the current date
        LocalDate date = LocalDate.now();
        System.out.println(date);

        Date d2 = new Date();

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        DateTimeFormatter format1 =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedCurrentDate = dateTime.format(format1);

        System.out.println("formatted current Date and" +
                " Time : " + formattedCurrentDate);

        // to get the current zone
        ZonedDateTime currentZone = ZonedDateTime.now();
        System.out.println("the current zone is " +
                currentZone.getZone());
        Thread.sleep(500);

        LocalDateTime dateTime2 = LocalDateTime.now();
        System.out.println(dateTime2);

        formattedCurrentDate = dateTime2.format(format1);

        System.out.println("formatted current Date and" +
                " Time : " + formattedCurrentDate);
        System.out.println("comparing two date times....");
        //System.out.println(dateTime - dateTime2);
        Date d = new Date();

        System.out.println("date : " + d);
        System.out.println("miliseconds : " + d.getTime());
        System.out.println("date : " + d2);
        System.out.println("miliseconds : " + d2.getTime());

        System.out.println("datetime diff : " + (d.getTime() - d2.getTime()));
    }
}
