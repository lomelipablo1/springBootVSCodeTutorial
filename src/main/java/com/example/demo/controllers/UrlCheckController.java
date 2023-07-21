package com.example.demo.controllers;

import java.net.HttpURLConnection;
import java.net.URI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String siteUp = "Site is up!";
    private final String siteDown = "Site is down!";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        // make a http connection
        String returnMessage = "";
        try {
            URI urlObj = new URI(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(conn.getResponseCode() < 400)
            {
                returnMessage = siteUp;
            }
            else
            {
                returnMessage = siteDown;
            }
            conn.disconnect();
        }
        catch(Exception e){
           returnMessage = e.toString();
        }

        return returnMessage;
    }
}
