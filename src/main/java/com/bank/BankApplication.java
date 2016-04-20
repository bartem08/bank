package com.bank;

import com.bank.config.CustomUserDetails;
import com.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository) throws Exception {
		builder
				.userDetailsService(userDetailsService(repository))
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	private UserDetailsService userDetailsService(final UserRepository repository) {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String inn) throws UsernameNotFoundException {
				return new CustomUserDetails(repository.findByInn(inn));
			}
		};
	}

}
