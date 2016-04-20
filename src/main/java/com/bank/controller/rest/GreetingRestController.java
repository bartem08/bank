package com.bank.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    @RequestMapping(value = "/bank/greeting", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity success() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

}
