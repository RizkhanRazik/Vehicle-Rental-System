

package com.company;
//Another Aggregation Class for Vehicle Class
public class Shedule {
    private int day;
    private int month;
    private int year;

    //The Three member Variables for the Shedule Class

    //The Public Constructor Shedule
    public Shedule(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        pickUpDate(day,month,year);
    }

    //Getter and Setter Methods for the 3 instance Varibles
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    //Initializing the Pick Up Date in a constructor
    public String pickUpDate(int day,int month, int year)
    {
        return day +"/"+month+"/"+year;
    }
    //Initializing the Drop Off Date in a constructor
    public String dropOffDate(int day,int month, int year){
        return day +"/"+month+"/"+year;
    }


}
