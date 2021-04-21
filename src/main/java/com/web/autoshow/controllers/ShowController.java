package com.web.autoshow.controllers;

import com.web.autoshow.common.Response;
import com.web.autoshow.dao.CarDAO;
import com.web.autoshow.dao.PersonDAO;
import com.web.autoshow.dao.ShowDAO;
import com.web.autoshow.dto.ShowDTO;
import com.web.autoshow.models.Car;
import com.web.autoshow.models.Person;
import com.web.autoshow.models.Show;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/show")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ShowController {
    private final Response response;
    private final ShowDAO showDAO;
    private final CarDAO carDAO;
    private final PersonDAO personDAO;

    public ShowController(Response response, ShowDAO showDAO, CarDAO carDAO, PersonDAO personDAO) {
        this.response = response;
        this.showDAO = showDAO;
        this.carDAO = carDAO;
        this.personDAO = personDAO;
    }

    @PostMapping
    public HashMap<String, Object> add(@RequestBody ShowDTO body) {
        Car car = carDAO.get(body.getCarId());
        Person person = personDAO.getPerson(body.getUserId());
        Show show = new Show(car, person, body.getDate());
        showDAO.add(show);

        response.push("resultCode", 1);

        return response.getResponse();
    }
}
