package com.bank.service.jpa;

import com.bank.model.Profile;
import com.bank.repository.jpa.ProfileRepository;
import com.bank.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile findByInn(final String inn) {
        return profileRepository.findByInn(inn);
    }

    @Override
    public Profile createUser(Profile profile) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        return profileRepository.save(profile);
    }

}
