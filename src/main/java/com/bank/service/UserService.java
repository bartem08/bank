package com.bank.service;

import com.bank.model.User;

import java.util.List;

public interface UserService {

    User findByInn(String inn);

    User findById(Integer id);

    void create(User user) throws Exception;

    List getUserAccounts(Integer id);

    void update(User user) throws Exception;

}
