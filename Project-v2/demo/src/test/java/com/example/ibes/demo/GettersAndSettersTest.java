package com.example.ibes.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GettersAndSettersTest {
    
    @Test
    public void test() {
        Car car = new Car();
        
        car.setId((long) 3);
        car.setName("Audi Q7");
        car.setPrice(12000);
        car.setHorsePower(245);
        car.setYear(2009);

        assertEquals((long) 3, car.getId());
        assertEquals("Audi Q7", car.getName());
        assertEquals(12000, car.getPrice());
        assertEquals(245, car.getHorsePower());
        assertEquals(2009, car.getYear());

        System.out.println("GettersAndSetters test was successful!");
    }
}
