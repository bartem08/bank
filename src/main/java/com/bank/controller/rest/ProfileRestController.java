package com.bank.controller.rest;

import com.bank.model.Profile;
import com.bank.model.dto.ProfileDTO;
import com.bank.service.ProfileManagement;
import com.bank.service.ProfileService;
import com.bank.service.jpa.ProfileManagementImpl;
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
    private ProfileManagement profileManagement;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity getUser(Principal principal) {
        return new ResponseEntity<>(new ProfileDTO(profileManagement.findByInn(principal.getName())), HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerUser(@RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("inn") String inn,
                                       @RequestParam("password") String password,
                                       @RequestParam("email") String email) {
        Profile profile = profileManagement.createUser(new Profile(firstName, lastName, inn, password, email));
        return new ResponseEntity<>(new ProfileDTO(profile), HttpStatus.CREATED);
    }

}
