package com.technicaltransition.ratelimiter.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerApi {

    @GetMapping("/rest/test")
    public String getData() {
        return "test data from rest api....";
    }
}
