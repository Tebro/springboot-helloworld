package com.example.demo;

import java.io.BufferedInputStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HelloWorldController {

    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World, I am: " + getComputerName();
    }

    private String getComputerName() {
        BufferedInputStream in = null;
        try {
            Runtime run = Runtime.getRuntime();
            Process proc = run.exec("hostname");
            in = new BufferedInputStream(proc.getInputStream());
            String retval = "";
            while (in.available() > 0) {
                retval += (char) in.read();
            }
            return retval;
        } catch (Exception e) {
            return "Unknown host";
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {

            }

        }

    }

}
