package com.example.demo;


import java.util.Random;

@RestController
public class LoadController {
    Random random = new Random();

    @RequestMapping("/load")
    public String[] load(){
        String[] retval = new String[1000000];
        for(int i = 0; i < 1000000; i++){
            retval[i] = "" + random.nextInt(100000);
        }

        return retval;
    }
}
