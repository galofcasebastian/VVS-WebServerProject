package com.example.ibes.demo;

import java.util.List;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@Api(name = "Car Controller API", description = "List of methods that manage cars", stage = ApiStage.RC)
public class CarController {

    private CarRepository carRepository;
   
    @Autowired
    public CarController(CarRepository carRepository) {
       this.carRepository = carRepository;
    }

    @RequestMapping(value = "/allCars", method = RequestMethod.GET)
    @ApiMethod(description = "Get all cars from the database")
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Get the car with the provided ID from the database")
    public Car getCarById(@ApiPathParam(name = "id") @PathVariable Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new VvsException(HttpStatus.NOT_FOUND, String.format("Person with id '%s' does not exist.", id)));
        return car;
    }

    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    @ApiMethod(description = "Get the car with the provided name from the database")
    public List<Car> getCarByName(@ApiPathParam(name = "name") @PathVariable String name) {
        return carRepository.findCarByName(name);
    }

    @RequestMapping(value = "/findByYear/{year}", method = RequestMethod.GET)
    @ApiMethod(description = "Get cars with the provided year from the database")
    public List<Car> getCarByYear(@ApiPathParam(name = "year") @PathVariable int year) {
        return carRepository.findCarByYear(year);
    }

    @RequestMapping(value = "/affordableCars/{price}", method = RequestMethod.GET)
    @ApiMethod(description = "Get all cars which the price is lower than the provided value")
    public List<Car> getAffordable(@ApiPathParam(name = "price") @PathVariable double price) {
       return carRepository.findByPriceLessThan(price);
    }
    
    @RequestMapping(value = "/addCar", method = RequestMethod.PUT)
    @ApiMethod(description = "Create a new car and add it to the database")
    public List<Car> addCar(@RequestBody Car car) {
        carRepository.save(car);
        return carRepository.findAll();
    }
    
    @RequestMapping(value = "/deleteCar/{id}", method = RequestMethod.POST)
    @ApiMethod(description = "Delete de car with the provided ID from de database")
    public List<Car> deleteCar(@ApiPathParam(name = "id") @PathVariable Long id) {
        carRepository.deleteById(id);
        return carRepository.findAll();
    }
}