package com.example.demo;

import org.springframework.stereotype.Component;
import java.io.BufferedInputStream;

@Component
class HostnameFinder {

    private String hostname = "";

    public String get(){
        if(this.hostname.length() < 1){
            this.hostname = getHostname();
        }
        return this.hostname;
    }

    
    private String getHostname(){
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