package com.bank.config;

import com.bank.model.Profile;
import com.bank.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfileService profileService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileService.findByInn(username);
        if (profile == null) {
            throw new UsernameNotFoundException("Profile not found");
        }
        return new CustomUserDetails(profile);
    }
}
