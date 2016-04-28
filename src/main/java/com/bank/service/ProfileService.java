package com.bank.service;

import com.bank.model.Profile;

public interface ProfileService {

    Profile findByInn(String inn);

    void createUser(Profile profile);

}
