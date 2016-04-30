package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Deposit;
import com.bank.model.Transfer;

import java.security.Principal;

public interface AccountManagement extends AccountService {

    Account updateBalance(Transfer transfer);

    Account updateBalance(Principal principal, Deposit deposit);

    Account closeDeposit(Deposit deposit);

    Transfer saveTransfer(Transfer transfer);

    Transfer getTransferById(Integer id);

    void deleteTransfer(Integer id);

}
