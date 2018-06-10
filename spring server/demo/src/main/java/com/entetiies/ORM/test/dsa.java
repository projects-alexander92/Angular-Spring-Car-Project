package com.entetiies.ORM.test;

import com.entetiies.ORM.enums.Rating;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class dsa {
    public static void main(String[] args) {
        Rating rating = Rating.FIVE;
        Rating rating1 = Rating.THREE;
        System.out.println(rating.getValue());
        System.out.println(rating1.getValue());
        System.out.println(Rating.values()[2]);
        System.out.println(Calendar.getInstance().get(Calendar.YEAR));
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 2055);
        System.out.println(calendar.getTime());
        System.out.println(calendar2.getTime());
    }
}
