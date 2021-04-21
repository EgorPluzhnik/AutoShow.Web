package com.web.autoshow.controllers;

import com.sun.mail.iap.ByteArray;
import com.web.autoshow.common.Response;
import com.web.autoshow.dao.AuthDAO;
import com.web.autoshow.dao.CarDAO;
import com.web.autoshow.dto.AuthDTO;
import com.web.autoshow.dto.CarDTO;
import com.web.autoshow.models.Auth;
import com.web.autoshow.models.Car;
import com.web.autoshow.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CarController {
    CarDAO carDAO;
    Response response;

    public CarController(CarDAO carDAO, Response response) {
        this.carDAO = carDAO;
        this.response = response;
    }

    // Получить список N машин
    @GetMapping
    public HashMap<String, Object> getWithLimit(@RequestParam int size) {
        if (size > carDAO.count()) {
            response.push("message", "The provided size is larger than the amount of cars");
            response.push("resultCode", 0);
        } else if (size < 1) {
            response.push("message", "The provided size must be higher than 0");
            response.push("resultCode", 0);
        } else {
            ArrayList<Car> cars = carDAO.getInAmountOf(size);
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
                           @Autowired AuthUtils authUtils,
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
    public void deleteCar(@PathVariable int id){

    }

    @GetMapping("/search")
    public HashMap<String, Object> search(@RequestParam String query) {
        ArrayList<HashMap<String, Object>> cars = carDAO.searchModels(query);
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
