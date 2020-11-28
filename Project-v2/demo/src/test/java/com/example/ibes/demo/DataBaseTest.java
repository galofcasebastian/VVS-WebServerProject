package com.example.ibes.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
public class DataBaseTest {

    @Autowired
    private CarRepository carsRepository;

    @BeforeEach
    public void initializeDataBase() {
        carsRepository.deleteAll();
    }
    
    @Test
    public void testFindAllCarsWhenDbIsEmpty() {
        List<Car> cars = carsRepository.findAll();
        assertEquals(0, cars.size());
    }
    
    @Test
    public void testFindAllCarsWhenDbIsNotEmpty() {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("BMW 320i", 7500, 175, 2008));
        cars.add(new Car("Dacia Logan", 2000, 75, 2011));
        cars.add(new Car("VW Passat", 7000, 140, 2007));
        cars.add(new Car("Ford Focus", 3200, 100, 2004));
        carsRepository.saveAll(cars);

        List<Car> cars2 = carsRepository.findAll();
        assertTrue((cars.containsAll(cars2) && cars2.containsAll(cars)));
    }
    
    @Test
    public void testSaveCarWhenDbIsEmpty() {
        Car car = new Car("BMW 320i", 7500, 175, 2008);
        Car addedCar = null;
        addedCar = carsRepository.save(car);
        List<Car> cars = carsRepository.findAll();
        assertNotNull(addedCar);
        assertEquals(1, cars.size());
    }
    
    @Test
    public void testSaveCarWhenDbIsNotEmpty() {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("BMW 320i", 7500, 175, 2008));
        cars.add(new Car("Dacia Logan", 2000, 75, 2011));
        cars.add(new Car("VW Passat", 7000, 140, 2007));
        cars.add(new Car("Ford Focus", 3200, 100, 2004));
        carsRepository.saveAll(cars);

        Car car = new Car("Mazda 3", 0, -70, 1000);
        cars.add(car);
        carsRepository.save(car);
        List<Car> cars2 = carsRepository.findAll();
        assertTrue((cars.containsAll(cars2) && cars2.containsAll(cars)));
        assertEquals(cars.size(), cars2.size());
    }
    
    @Test
    public void testFindByPriceLessThan() {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("BMW 320i", 7500, 175, 2008));
        cars.add(new Car("Dacia Logan", 2000, 75, 2011));
        cars.add(new Car("VW Passat", 7000, 140, 2007));
        cars.add(new Car("Ford Focus", 3200, 100, 2004));
        carsRepository.saveAll(cars);

        double price = 5000;
        List<Car> cars2 = null;
        cars2 = carsRepository.findByPriceLessThan(price);

        assertEquals(2, cars2.size());
    }

    @Test
    public void testDeleteCarByIdWhenIdExist() {

        List<Car> cars = new ArrayList<>();

        cars.add(new Car("BMW 320i", 7500, 175, 2008));
        cars.add(new Car("Dacia Logan", 2000, 75, 2011));
        cars.add(new Car("VW Passat", 7000, 140, 2007));
        cars.add(new Car("Ford Focus", 3200, 100, 2004));
        carsRepository.saveAll(cars);

        Long id = cars.get(0).getId();
        carsRepository.deleteById(id);
        cars.remove(0);
        List<Car> cars2 = carsRepository.findAll();

        assertTrue((cars.containsAll(cars2) && cars2.containsAll(cars)));
    }
    
    @Test
    public void testDeletecarByIdWhenIdNotExist() {

        List<Car> cars = new ArrayList<>();

        cars.add(new Car("BMW 320i", 7500, 175, 2008));
        cars.add(new Car("Dacia Logan", 2000, 75, 2011));
        cars.add(new Car("VW Passat", 7000, 140, 2007));
        cars.add(new Car("Ford Focus", 3200, 100, 2004));
        carsRepository.saveAll(cars);

        Long id = (long) 7;
        assertThrows(EmptyResultDataAccessException.class, () -> carsRepository.deleteById(id));
    }
}

