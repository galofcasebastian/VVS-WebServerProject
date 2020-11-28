package com.example.ibes.demo;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private double price;
    private int horsePower;
    private int year;
    
    public Car() {}

    public Car(String name, double price, int horsePower, int year) {
        this.name = name;
        this.price = price;
        this.horsePower = horsePower;
        this.year = year;
    }

    public Car(Long id, String name, double price, int horsePower, int year) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.horsePower = horsePower;
        this.year = year;
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) 
            return true;
        if (o == null || getClass() != o.getClass()) 
            return false;
        Car car = (Car) o;

       return   id != null &&  Double.compare(car.price, price) == 0
               && Objects.equals(id, car.id) && Objects.equals(name, car.name)
               && Objects.equals(horsePower, car.horsePower);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, horsePower);
    }
}