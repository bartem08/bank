package com.bank.service.jpa;

import com.bank.model.*;
import com.bank.repository.jpa.*;
import com.bank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

@Service
public class ProfileManagementImpl implements ProfileManagement {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Profile findByInn(String inn) {
        return profileRepository.findByInn(inn);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED,
            transactionManager = "transactionManager",
            rollbackFor = Exception.class)
    public void createUser(Profile profile) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profileRepository.save(profile);
        createRole(new Role(profile, "ROLE_USER"));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.MANDATORY,
            transactionManager = "transactionManager",
            rollbackFor = Exception.class)
    public void createRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.delete(id);
    }

    @Override
    public Role getRoleByRoleName(String role) {
        return roleRepository.findRoleByRole(role);
    }
}
