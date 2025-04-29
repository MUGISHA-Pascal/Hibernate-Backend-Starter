package com.hibernate.hibernate.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("bike")
public class Bike extends Vehicle{
    private boolean hasCarriers;
    public boolean isHasCarrier(){
        return hasCarriers;
    }
    public void setHasCarrier(boolean hasCarriers){
        this.hasCarriers=hasCarriers;
    }
}
