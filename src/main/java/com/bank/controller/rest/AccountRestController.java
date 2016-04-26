package com.bank.controller.rest;

import com.bank.model.*;
import com.bank.model.dto.AccountDTO;
import com.bank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bank/accounts")
public class AccountRestController {

    @Autowired
    private AccountManagement accountManagement;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAccount(Principal principal) {
        try {
            accountManagement.createAccount(new Account(profileService.findByInn(principal.getName())));
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAccounts(Principal principal) {
        List<AccountDTO> accountDTOs = new ArrayList<>();
        try {
            List<Account> accountList = accountManagement.getAccountByProfileInn(principal.getName());
            accountDTOs.addAll(accountList.stream().map(AccountDTO::new).collect(Collectors.toList()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(accountDTOs, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.clientHasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}/operations/out", method = RequestMethod.PUT)
    public ResponseEntity addTransferOut(@PathVariable("id") Integer id,
                                         Principal principal,
                                         @RequestBody @Valid Transfer transfer,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        try {
            final Account account = accountManagement.getAccountById(id);
            if (account.getProfile() != profileService.findByInn(principal.getName())) {
                return new ResponseEntity(HttpStatus.LOCKED);
            } else {
                transfer.setFromAccount(account);
                transfer.setToAccount(accountManagement.getAccountById(account.getProfile().getId()));
                accountManagement.updateBalance(transfer);
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

}
