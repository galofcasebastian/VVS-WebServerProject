package com.example.ibes.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    private CarRepository carsRepository;

    @LocalServerPort
    private int port;

    private String serverUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @BeforeEach
    public void initServerURL() {
        carsRepository.deleteAll();
        this.serverUrl = "http://localhost:" + port;
    }
    
    private ResponseEntity<List<Car>> executeCarsRequest(String url, HttpMethod method) {
        return restTemplate.exchange(serverUrl + url, method, null, new ParameterizedTypeReference<>() {
        });
    }
    
    @Test
    public void whenGetAllCarsWithEmptyDb_thenReturn200AndCorrectResponse() {
        ResponseEntity<List<Car>> response = executeCarsRequest("/allCars", HttpMethod.GET);
        assertEquals(HttpStatus.valueOf(200), response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }
    
    @Test
    public void whenGetAllCarsWithPopulatedDb_thenReturn200AndCorrectResponse() {
        List<Car> cars = Arrays.asList(new Car("BMW 320i", 7500, 175, 2008), new Car("Dacia Logan", 2000, 75, 2011), new Car("VW Passat", 7000, 140, 2007), new Car("Ford Focus", 3200, 100, 2004)); 
        carsRepository.saveAll(cars);
        ResponseEntity<List<Car>> response = executeCarsRequest("/allCars", HttpMethod.GET);
        assertEquals(HttpStatus.valueOf(200), response.getStatusCode());
        List<Car> responseCarsList = response.getBody();
        assertTrue((responseCarsList.containsAll(cars) && cars.containsAll(responseCarsList)));
    }

    @Test
    public void whenGetFindCarsWithoutPathVariable_thenReturn404() {
        HttpClientErrorException response = assertThrows(HttpClientErrorException.class, () -> executeCarsRequest("/affordableCars", HttpMethod.GET));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void whenRemoveCarWithoutPathVariable_thenReturn404() throws Exception {
        HttpClientErrorException response = assertThrows(HttpClientErrorException.class, () -> executeCarsRequest("/deleteCar", HttpMethod.DELETE));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

