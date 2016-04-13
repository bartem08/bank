package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Operation;

import java.util.List;

public interface AccountService {

    void createAccount(Account account) throws Exception;

    Account findById(Integer id);

    List findByUserId(Integer id);

    void deleteAccount(Integer id) throws Exception;

}
