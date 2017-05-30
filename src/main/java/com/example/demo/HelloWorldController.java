package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HelloWorldController {

    @Autowired
    HostnameFinder hostnameFinder;

    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World, I am: " + hostnameFinder.get();
    }

}
