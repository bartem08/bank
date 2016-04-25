package com.bank.service;

import com.bank.model.Profile;

public interface ProfileService {

    Profile findByInn(String inn);

    Profile findById(Integer id);

    Profile createUser(Profile profile) throws Exception;

}
