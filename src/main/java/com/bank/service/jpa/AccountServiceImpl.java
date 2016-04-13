package com.bank.service.jpa;

import com.bank.model.Account;
import com.bank.model.Operation;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void createAccount(Account account) throws Exception {
        accountRepository.createAccount(account);
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id);
    }

    @Override
    public List findByUserId(Integer id) {
        return accountRepository.findByUserId(id);
    }

    @Override
    public void deleteAccount(Integer id) throws Exception {
        accountRepository.deleteAccount(id);
    }
}
