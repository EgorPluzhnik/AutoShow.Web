package com.web.autoshow.repositories;

import com.web.autoshow.models.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
