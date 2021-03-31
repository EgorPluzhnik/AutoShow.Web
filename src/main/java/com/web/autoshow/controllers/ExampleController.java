package com.web.autoshow.controllers;

import com.web.autoshow.models.ExampleModel;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class ExampleController {

    @GetMapping("/")
    public ExampleModel start(){
        return new ExampleModel(555, "Hello!");
    }
}
