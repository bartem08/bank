package com.bank.service;

import com.bank.model.Deposit;
import com.bank.model.Transfer;

public interface AccountManagement extends AccountService, OperationService {

    void updateBalance(Transfer transfer);

    void updateBalance(Deposit deposit);

}
