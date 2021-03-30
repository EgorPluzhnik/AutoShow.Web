package com.web.autoshow.controllers;

import com.web.autoshow.models.ExampleModel;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class ExampleController {

    @GetMapping("/")
    public ExampleModel start(){
        return new ExampleModel(555, "Hello!");
    }

    /*@GetMapping("/hello")
    public String hello(Model model) {
        ArrayList<String> list = new ArrayList<>();
        list.add("React");
        list.add("Redux");
        list.add("Developer");
        model.addAttribute("words", list);
        return "../templates/examples/hello.html";
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "../templates/examples/goodbye.html";
    }*/
}
