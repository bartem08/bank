package com.bank;

import com.bank.config.CustomUserDetails;
import com.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@EnableResourceServer
public class BankApplication extends ResourceServerConfigurerAdapter {

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
		return inn -> new CustomUserDetails(repository.findByInn(inn));
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/bank/users/register")
				.permitAll()
				.anyRequest()
				.authenticated();
	}

}
