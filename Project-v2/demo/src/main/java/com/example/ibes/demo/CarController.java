package com.example.ibes.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarRepository carRepository;
   
    @Autowired
    public CarController(CarRepository carRepository) {
       this.carRepository = carRepository;
    }

    @GetMapping("/allCars")
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @GetMapping("findById/{id}")
    public Car getCarById(@PathVariable Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new VvsException(HttpStatus.NOT_FOUND, String.format("Person with id '%s' does not exist.", id)));
        return car;
    }

    @GetMapping("/findByName/{name}")
    public List<Car> getCarByName(@PathVariable String name) {
        return carRepository.findCarByName(name);
    }

    @GetMapping("/findByYear/{year}")
    public List<Car> getCarByYear(@PathVariable int year) {
        return carRepository.findCarByYear(year);
    }

    @GetMapping("/affordableCars/{price}")
    public List<Car> getAffordable(@PathVariable double price) {
       return carRepository.findByPriceLessThan(price);
    }
    
    @PutMapping("/addCar")
    public List<Car> addCar(@RequestBody Car car) {
        carRepository.save(car);
        return carRepository.findAll();
    }
    
    @GetMapping("/deleteCar/{id}")
    public List<Car> deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return carRepository.findAll();
    }
}