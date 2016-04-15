package com.bank.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    @RequestMapping("/bank/greeting")
    public ResponseEntity success() {
        return new ResponseEntity<>("HELLO", HttpStatus.OK);
    }

}
