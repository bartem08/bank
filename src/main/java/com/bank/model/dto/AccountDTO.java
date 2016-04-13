package com.bank.model.dto;

import com.bank.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {

    private float balance;

    private List<OperationDTO> operations = new ArrayList<>();

    public AccountDTO(Account account) {
        setBalance(account.getBalance());
        if (account.getOperations() != null)
            operations.addAll(account.getOperations().stream().map(OperationDTO::new).collect(Collectors.toList()));
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<OperationDTO> getOperations() {
        return operations;
    }

    public static List<AccountDTO> toListDTO (List<Account> list) {
        return list.stream().map(AccountDTO::new).collect(Collectors.toList());
    }
}
