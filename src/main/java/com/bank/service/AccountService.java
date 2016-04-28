package com.bank.service;

import com.bank.model.Account;

import java.util.List;

public interface AccountService {

    Account createAccount(Account account);

    List<Account> getAccountByProfileInn(String userInn);

    Account getAccountById(Integer accountId) throws RuntimeException;

}
