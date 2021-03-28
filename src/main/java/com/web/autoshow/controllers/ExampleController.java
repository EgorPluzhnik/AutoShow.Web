package com.web.autoshow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class ExampleController {
    @GetMapping("/hello")
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
    }
}
