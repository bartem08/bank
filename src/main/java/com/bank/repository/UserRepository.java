package com.bank.repository;

import com.bank.model.*;

import java.util.List;

public interface UserRepository {

    void createUser(User user) throws Exception;

    User findByInn(String inn);

    User findById(Integer id);

    void updateUser(User user) throws Exception;

    List findUserAccounts(Integer id);

}
