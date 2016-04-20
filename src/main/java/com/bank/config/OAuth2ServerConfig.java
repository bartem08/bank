package com.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.config.annotation.web.configurers.*;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

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
        clients.inMemory().withClient("my-trusted-client")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust")
                .resourceIds("oauth2-resource").accessTokenValiditySeconds(100).and()
                .withClient("my-client-with-registered-redirect").authorizedGrantTypes("authorization_code")
                .authorities("ROLE_CLIENT").scopes("read", "trust").resourceIds("oauth2-resource")
                .redirectUris("http://anywhere?key=value").and().withClient("my-client-with-secret")
                .authorizedGrantTypes("client_credentials", "password").authorities("ROLE_CLIENT").scopes("read")
                .resourceIds("oauth2-resource").secret("secret");
    }

}
