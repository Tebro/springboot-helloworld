package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HelloWorldController {

    @Value("${hello.name}")
    private String helloName;

    @Autowired
    HostnameFinder hostnameFinder;

    @RequestMapping("/")
    public String helloWorld() {
        return "Hello" + helloName +" , I am: " + hostnameFinder.get();
    }

}
