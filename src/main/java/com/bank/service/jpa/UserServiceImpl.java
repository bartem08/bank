package com.bank.service.jpa;

import com.bank.model.User;
import com.bank.repository.UserRepository;
import com.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByInn(final String inn) {
        return userRepository.findByInn(inn);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
