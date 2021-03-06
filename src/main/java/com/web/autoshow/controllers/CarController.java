package com.web.autoshow.controllers;

import com.web.autoshow.common.Response;
import com.web.autoshow.dao.AuthDAO;
import com.web.autoshow.dao.CarDAO;
import com.web.autoshow.dto.CarDTO;
import com.web.autoshow.models.Auth;
import com.web.autoshow.models.Car;
import com.web.autoshow.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CarController {
    private final CarDAO carDAO;
    private Response response;
    private final AuthUtils authUtils;

    public CarController(CarDAO carDAO, Response response, AuthUtils authUtils) {
        this.carDAO = carDAO;
        this.response = response;
        this.authUtils = authUtils;
    }

    // Получить список N машин
    @GetMapping
    public HashMap<String, Object> getWithLimit(@RequestParam int offset,
                                                @RequestParam int limit) {
        if (limit > carDAO.count() || offset > carDAO.count()) {
            response.push("message", "The provided offset and limit is larger than the amount of cars");
            response.push("resultCode", 0);
        }
        else if (limit < 0 || offset < 0) {
            response.push("message", "The provided offset and limit must be higher than 0");
            response.push("resultCode", 0);
        }
        else {
            ArrayList<Car> cars = carDAO.getInRange(offset, limit);
            response.push("cars", cars);
            response.push("message", "Success");
            response.push("resultCode", 1);
        }

        return response.getResponse();
    }

    // Добавить машину
    @PostMapping
    public HashMap<String, Object> addCar(@RequestBody CarDTO carDTO,
                           @Autowired Response response,
                           AuthDAO authDAO,
                           HttpServletRequest req,
                           CarDAO carDAO) {
        // Не тестил
        Cookie pidCookie = authUtils.findPidCookie(req.getCookies());
        if (pidCookie != null && !pidCookie.getValue().equals("")) {
            long pid = authUtils.decipher(pidCookie.getValue());
            Auth auth = authDAO.getAuth(pid);
            if (auth != null && auth.getAdminStatus()) {
                Car car = new Car(carDTO.getModel(), carDTO.getDescription(), carDTO.getReleaseDate(), "");
                carDAO.add(car);
                response.push("resultCode", 1);
                return response.getResponse();
            }
        }

        response.push("resultCode", 0);
        response.push("message", "Not allowed");
        return response.getResponse();
    }

    // Получить конкретную машину по её ид
    @GetMapping("/{id}")
    public HashMap<String, Object> getSingle(@PathVariable long id) {
        Car car = carDAO.get(id);
        if (car != null) {
            response.push("car", car);
            response.push("resultCode", 1);
        } else {
            response.push("message", "Not found");
            response.push("resultCode", 0);
        }

        return response.getResponse();
    }

    // Удалить конкретную машину по ид
    @DeleteMapping("/{id}")
    public HashMap<String, Object> deleteCar(@PathVariable long id){
        Boolean isDeleteDone = carDAO.deleteCar(id);

        if (isDeleteDone) {
            response.push("message", "Deletion was successful");
            response.push("resultCode", 1);
        }
        else {
            response.push("message", "Car not found");
            response.push("resultCode", 0);
        }

        return response.getResponse();
    }

    // Найти все машины по данному названию
    @GetMapping("/search")
    public HashMap<String, Object> search(@RequestParam String query) {
        ArrayList<HashMap<String, Object>> cars = carDAO.searchModels(query);
        response = new Response();
        if (cars != null) {
            response.push("cars", cars);
            response.push("message", "Success");
            response.push("resultCode", 1);
        } else {
            response.push("message", "No matches");
            response.push("resultCode", 0);
        }

        return response.getResponse();
    }
}
