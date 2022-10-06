package ru.kuzmina.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public void authConfig(AuthenticationManagerBuilder authBuilder,
                           UserDetailsServiceImpl userDetailsService,
                           PasswordEncoder passwordEncoder) throws Exception {
        authBuilder.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .and()
                .withUser("guest")
                .password(passwordEncoder.encode("guest"))
                .roles("GUEST");

        authBuilder.userDetailsService(userDetailsService);
    }

}
