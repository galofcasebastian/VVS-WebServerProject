package com.example.ibes.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBase implements CommandLineRunner {
    
    private CarRepository carsRepository;

    @Autowired
    public DataBase(CarRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Audi A6", 10000, 204, 2011));
        cars.add(new Car("BMW 530d", 12000, 265, 2011));
        cars.add(new Car("VW Passat", 7000, 140, 2007));
        cars.add(new Car("Mercedes E220", 9000, 180, 2009));
        carsRepository.saveAll(cars);
    }
}
