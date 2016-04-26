package com.bank.controller.rest;

import com.bank.model.Profile;
import com.bank.model.dto.ProfileDTO;
import com.bank.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/bank/users")
public class ProfileRestController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity getUser(Principal principal) {
        return new ResponseEntity<>(new ProfileDTO(profileService.findByInn(principal.getName())), HttpStatus.OK);
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
