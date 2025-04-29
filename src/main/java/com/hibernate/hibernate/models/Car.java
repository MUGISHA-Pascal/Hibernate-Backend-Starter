package com.hibernate.hibernate.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("car")
public class Car extends Vehicle{
    private int numOfdoors;
    public int getNumOfdoors(){
        return numOfdoors;
    }
    public void setNumOfdoors(int numOfdoors){
        this.numOfdoors = numOfdoors;
    }
}

