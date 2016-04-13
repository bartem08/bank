package com.bank.controller.rest;

import com.bank.model.Account;
import com.bank.model.Response;
import com.bank.model.User;
import com.bank.model.dto.AccountDTO;
import com.bank.model.dto.UserDTO;
import com.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bank/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/inn", method = RequestMethod.POST)
    public ResponseEntity getUserByInn(@RequestParam("inn") String inn) {
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response> createUser(@RequestBody @Valid User user,
                                               BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(new Response(result.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
        try {
            userService.create(user);
            return new ResponseEntity<>(new Response("Success"), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Conflict"), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/{id}/accounts", method = RequestMethod.GET)
    public ResponseEntity getUserAccounts(@PathVariable("id") Integer id) {
        final List entities = userService.getUserAccounts(id);
        if (entities == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AccountDTO.toListDTO(entities), HttpStatus.OK);
    }

}
