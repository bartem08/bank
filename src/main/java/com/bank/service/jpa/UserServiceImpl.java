package com.bank.service.jpa;

import com.bank.model.User;
import com.bank.repository.UserRepository;
import com.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void create(final User user) throws Exception {
        userRepository.createUser(user);
    }

    @Override
    public List getUserAccounts(Integer id) {
        return userRepository.findUserAccounts(id);
    }

    @Override
    public void update(final User user) throws Exception {
        userRepository.updateUser(user);
    }

}
