package com.bank.controller.rest;

import com.bank.model.Profile;
import com.bank.model.dto.ProfileDTO;
import com.bank.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bank/users")
public class ProfileRestController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/me", method = RequestMethod.POST)
    public ResponseEntity getUserByInn(@RequestBody String inn) {
        final Profile profile = profileService.findByInn(inn);
        if (profile == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new ProfileDTO(profile), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getUserById(@PathVariable("id") Integer id) {
        final Profile profile = profileService.findById(id);
        if (profile == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new ProfileDTO(profile), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerUser(@RequestBody @Valid Profile profile,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        try {
            profileService.createUser(profile);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
