package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HelloWorldController {

    @RequestMapping("/")
    public String helloWorld(){
        return "Hello World";
    }

}
