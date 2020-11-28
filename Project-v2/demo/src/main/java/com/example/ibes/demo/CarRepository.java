package com.example.ibes.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByPriceLessThan(double price);

    List<Car> findCarById(Long id);

    List<Car> findCarByName(String name);

    List<Car> findCarByYear(int year);
}