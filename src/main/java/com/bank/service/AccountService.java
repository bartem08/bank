package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Profile;

import java.util.List;

public interface AccountService {

    Account createAccount(Account account);

    List<Account> getAccountByProfileInn(String userInn);

    Account getAccountById(Integer accountId) throws RuntimeException;

    Profile getProfileByAccountId(Integer id);

}
