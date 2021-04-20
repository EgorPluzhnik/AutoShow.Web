package com.web.autoshow.dao;

import com.web.autoshow.models.Car;
import com.web.autoshow.repositories.CarRepository;
import org.springframework.stereotype.Component;

@Component
public class CarDAO {
    private final CarRepository carRepo;

    public CarDAO(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    public void add(Car car) {
        if (car != null) {
            carRepo.save(car);
        }
    }
}
