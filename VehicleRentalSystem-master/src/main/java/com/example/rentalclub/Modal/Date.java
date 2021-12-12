package com.example.rentalclub.Modal;

public class Date {
   private java.util.Date pickUp;
   private java.util.Date dropOff;

    public Date(java.util.Date pickUp, java.util.Date dropOff) {
        this.pickUp = pickUp;
        this.dropOff = dropOff;
    }

    public java.util.Date getPickUp() {
        return pickUp;
    }

    public void setPickUp(java.util.Date pickUp) {
        this.pickUp = pickUp;
    }

    public java.util.Date getDropOff() {
        return dropOff;
    }

    public void setDropOff(java.util.Date dropOff) {
        this.dropOff = dropOff;
    }
}
