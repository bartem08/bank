package com.bank.controller.rest;

import com.bank.exception.AccessLockedException;
import com.bank.model.*;
import com.bank.model.dto.*;
import com.bank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bank/accounts")
public class AccountRestController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AccountManagement accountManagement;

    @Autowired
    private ProfileService profileService;

    private Map<Integer, Integer> codes = new HashMap<>(1000);

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAccount(Principal principal) {
        try {
            final Account account
                    = accountManagement.createAccount(new Account(profileService.findByInn(principal.getName())));
            return new ResponseEntity<>(new AccountDTO(account), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAccounts(Principal principal) {
        try {
            List<AccountDTO> accountDTOs = new ArrayList<>();
            List<Account> accountList = accountManagement.getAccountByProfileInn(principal.getName());
            accountDTOs.addAll(accountList.stream().map(AccountDTO::new).collect(Collectors.toList()));
            return new ResponseEntity<>(accountDTOs, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("#oauth2.clientHasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}/operations/out", method = RequestMethod.PUT)
    public ResponseEntity transferRequest(@PathVariable("id") Integer id,
                                         @RequestParam("to_id") Integer toId,
                                         @RequestParam("saldo") float saldo,
                                         @RequestParam(value = "description", required = false) String description,
                                         Principal principal) {
        try {
            final Account from = checkAccount(principal, id);
            final Account to = accountManagement.getAccountById(toId);
            final Transfer transfer = accountManagement.saveTransfer(
                    new Transfer(from, to, Calendar.getInstance().getTime(), saldo, description)
            );
            final Profile profile = profileService.findByInn(principal.getName());
            codes.put(transfer.getId(), emailService.sendMail(profile.getEmail()));
            return new ResponseEntity<>(new Response("success", "ok"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response(ex.getMessage(), "conflict"), HttpStatus.CONFLICT);
        }

    }

    @PreAuthorize("#oauth2.clientHasRole('ROLE_USER')")
    @RequestMapping(value = "/operations/out/{id}", method = RequestMethod.PUT)
    public ResponseEntity addTransferOut(@PathVariable("id") Integer id,
                                         @RequestParam("code") Integer code,
                                         Principal principal) {
            try {
                final Transfer transfer = accountManagement.getTransferById(id);
                checkAccount(principal, transfer.getFromAccount().getId());
                checkVerificationCode(transfer.getId(), code);
                accountManagement.updateBalance(transfer);
                return new ResponseEntity<>(new Response("success", "ok"), HttpStatus.OK);
            } catch (AccessLockedException ex) {
                accountManagement.deleteTransfer(id);
                return new ResponseEntity<>(new Response(ex.getMessage(), "locked"), HttpStatus.LOCKED);
            } catch (Exception ex) {
                return new ResponseEntity<>(new Response(ex.getMessage(), "conflict"), HttpStatus.CONFLICT);
            }
    }

    @RequestMapping(value = "/{id}/operations/out", method = RequestMethod.GET)
    public ResponseEntity getTransfersOut(@PathVariable("id") Integer id,
                                         Principal principal) {
        try {
            final Account account = checkAccount(principal, id);
            List<TransferDTO> transfers = account
                    .getOutTransfers()
                    .stream()
                    .map(TransferDTO::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(transfers, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/{id}/operations/in", method = RequestMethod.GET)
    public ResponseEntity getTransfersIn(@PathVariable("id") Integer id,
                                          Principal principal) {
        try {
            final Account account = checkAccount(principal, id);
            List<TransferDTO> transfers = account
                    .getInTransfers()
                    .stream()
                    .map(TransferDTO::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(transfers, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/{id}/operations/deposit", method = RequestMethod.GET)
    public ResponseEntity getDeposits(@PathVariable("id") Integer id,
                                          Principal principal) {
        try {
            final Account account = checkAccount(principal, id);
            List<DepositDTO> deposits = account
                    .getDeposits()
                    .stream()
                    .map(DepositDTO::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(deposits, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("#oauth2.clientHasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}/operations/deposit", method = RequestMethod.PUT)
    public ResponseEntity addDeposit(@PathVariable("id") Integer id,
                                     @RequestParam("saldo") float saldo,
                                     Principal principal) {
        try {
            final Account account = checkAccount(principal, id);
            accountManagement.updateBalance(principal, new Deposit(account, Calendar.getInstance().getTime(), saldo));
            return new ResponseEntity<>(new Response("success", "ok"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response(ex.getMessage(), "conflict"), HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("#oauth2.clientHasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}/operations/deposit/close", method = RequestMethod.PUT)
    public ResponseEntity closeDeposit(@PathVariable("id") int id,
                                       @RequestParam("d_id") int dId,
                                       Principal principal) {
        try {
            final Account account = checkAccount(principal, id);
            List<Deposit> deposits = account.getDeposits();
            deposits.stream()
                    .filter(deposit -> deposit.getId() == dId)
                    .forEach(deposit -> accountManagement.closeDeposit(deposit));
            return new ResponseEntity<>(new Response("success", "ok"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response(ex.getMessage(), "conflict"), HttpStatus.CONFLICT);
        }

    }

    private Account checkAccount(Principal principal, int id) throws RuntimeException {
        final Account account = accountManagement.getAccountById(id);
        if (!account.getProfile().getInn().equals(principal.getName())) {
            throw new AccessLockedException("Attempt to access another's resources");
        }
        return account;
    }

    private void checkVerificationCode(Integer transferId, Integer code) throws RuntimeException {
        Integer verify = codes.get(transferId);
        codes.remove(transferId);
        if (!Objects.equals(code, verify)) {
            throw new AccessLockedException("Invalid code. Try again ...");
        }
    }

}
