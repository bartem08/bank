package com.bank;

import com.bank.config.CustomUserDetails;
import com.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.*;

@SpringBootApplication
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
		return inn -> new CustomUserDetails(repository.findByInn(inn));
	}

	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints
					.authenticationManager(authenticationManager)
					.approvalStoreDisabled();
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.checkTokenAccess("isAuthenticated()");
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
					.withClient("android-client")
					.authorizedGrantTypes("password", "implicit", "authorization_code")
					.scopes("trust")
					.resourceIds("oauth2-resource")
					.accessTokenValiditySeconds(600)
					.secret("android-client");
		}

	}

	@Configuration
	@EnableResourceServer
	protected static class ResourceServer extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
					.csrf()
					.disable()
					.authorizeRequests()
					.antMatchers("/bank/users/register")
					.permitAll()
					.anyRequest()
					.authenticated();
		}
	}

}
