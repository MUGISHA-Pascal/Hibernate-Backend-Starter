package com.hibernate.hibernate.models;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="vehicle_type",discriminatorType = DiscriminatorType.STRING)
public class Vehicle {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;
   private String manufacturer;
   public int getVehicleId(){
       return vehicleId;
   }
   public void setVehicleId(int vehicleId){
       this.vehicleId=vehicleId;
   }
   public String getManufacturer(){
       return manufacturer;
   }
   public void setManufacturer(String manufacturer){
       this.manufacturer=manufacturer;
   }
}
