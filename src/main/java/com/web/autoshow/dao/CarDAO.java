package com.web.autoshow.dao;

import com.web.autoshow.models.Car;
import com.web.autoshow.repositories.CarRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

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

    public Car get(long id) {
        if (carRepo.findById(id).isPresent())
            return carRepo.findById(id).get();
        else
            return null;
    }

    public long count() {
        return carRepo.count();
    }

    public ArrayList<Car> getInAmountOf(long size) {
        ArrayList<Car> cars = new ArrayList<>();
        AtomicLong id = new AtomicLong();

        carRepo.findAll().forEach(car -> {
            if (id.get() < size) {
                cars.add(car);
                id.getAndIncrement();
            }
        });

        return cars;
    }

    public ArrayList<Car> getInRange(long offset, long limit) {
        ArrayList<Car> requestedCars = new ArrayList<>();
        var allCars = carRepo.findAll();

        int counter = 0;
        long maxIndex = offset + limit - 1;
        for (Car car: allCars) {
            if (counter < offset) continue;
            if (counter > maxIndex) break;

            requestedCars.add(car);
            counter++;
        }

        return requestedCars;
    }

    public ArrayList<HashMap<String, Object>> searchModels(String query) {
        ArrayList<HashMap<String, Object>> array = new ArrayList<>();
        carRepo.findAll().forEach(car -> {
            String model = car.getModel();
            if (model.matches("^" + query + ".*")) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("id", car.getCarId());
                data.put("model", model);
                array.add(data);
            }
        });

        if (array.size() == 0)
            return null;

        return array;
    }

    public Boolean deleteCar(long id) {
        Car carToDelete = get(id);
        if (carToDelete == null) {
            return false;
        }

        carRepo.delete(carToDelete);
        return true;
    }
}
