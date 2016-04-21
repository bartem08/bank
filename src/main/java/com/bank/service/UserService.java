package com.bank.service;

import com.bank.model.User;

import java.util.List;

public interface UserService {

    User findByInn(String inn);

    User findById(Integer id);

    User createUser(User user) throws Exception;

}
