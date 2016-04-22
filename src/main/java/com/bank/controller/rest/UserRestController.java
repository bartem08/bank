package com.bank.controller.rest;

import com.bank.model.User;
import com.bank.model.dto.UserDTO;
import com.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bank/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/me", method = RequestMethod.POST)
    public ResponseEntity getUserByInn(@RequestBody String inn) {
        final User user = userService.findByInn(inn);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getUserById(@PathVariable("id") Integer id) {
        final User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerUser(@RequestBody @Valid User user,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        try {
            user = userService.createUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
