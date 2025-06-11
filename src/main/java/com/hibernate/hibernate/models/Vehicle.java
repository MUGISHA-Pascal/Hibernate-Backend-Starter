package com.hibernate.hibernate.models;

import jakarta.persistence.*;
import java.lang.*;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="vehicle_type",discriminatorType = DiscriminatorType.STRING)
public class Vehicle {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer vehicleId;
private String manufacturer;
   public Integer getVehicleId(){
       return vehicleId;
   }
   public void setVehicleId(Integer vehicleId){
       this.vehicleId=vehicleId;
   }
   public String getManufacturer(){
       return manufacturer;
   }
   public void setManufacturer(String manufacturer){
       this.manufacturer=manufacturer;
   }
}
