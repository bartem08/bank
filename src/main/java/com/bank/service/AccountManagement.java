package com.bank.service;

import com.bank.model.Deposit;
import com.bank.model.Transfer;

import java.security.Principal;

public interface AccountManagement extends AccountService {

    void updateBalance(Transfer transfer);

    void updateBalance(Principal principal, Deposit deposit);

    void closeDeposit(Deposit deposit);

}
