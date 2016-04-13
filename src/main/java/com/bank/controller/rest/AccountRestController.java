package com.bank.controller.rest;

import com.bank.model.Account;
import com.bank.model.dto.AccountDTO;
import com.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/bank/accounts")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getAccount(@PathVariable("id") Integer id) {
        final Account account = accountService.findById(id);
        if (account == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new AccountDTO(account), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody @Valid Account account,
                                        BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        try {
            accountService.createAccount(account);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

}
