package com.web.autoshow.controllers;

import com.sun.mail.iap.ByteArray;
import com.web.autoshow.dto.CarDTO;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CarController {
    private final CarDTO exampleCar = new CarDTO(6, new ByteArray(5), "Ferrari F40",
            "First race car in the world!", new Date(4213123123L));

    @GetMapping
    public List<CarDTO> GetAllCarInfo() {
        var result = new ArrayList<CarDTO>();
        result.add(exampleCar);
        result.add(exampleCar);
        return result;
    }

    @PostMapping
    public void addCar(@RequestBody CarDTO car) {

    }

    @GetMapping("/{id}")
    public CarDTO getCarInfo(@PathVariable int id) {
        return new CarDTO(id, exampleCar.getImage(), exampleCar.getModel(),
                exampleCar.getDescription(), exampleCar.getReleaseDate());
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id){

    }
}
