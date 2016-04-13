package com.bank.repository;

import com.bank.model.*;

import java.util.List;

public interface AccountRepository {

    void createAccount(Account account) throws Exception;

    Account findById(Integer id);

    List findByUserId(Integer id);

    void deleteAccount(Integer id) throws Exception;

}
