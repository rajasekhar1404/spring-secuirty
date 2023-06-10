package com.krs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    /**
     * Filter chain method takes a HttpSecurity as param and returns SecurityFilterChain
     * We are using Lambda DSL for authorizing the requests
     * Using form login to let the user login
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // anyone can access any url
        // http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());

        // anyone can access '/' url only authorized users can access remaining all urls
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/employee").hasRole("EMPLOYEE")
                .requestMatchers("/emp-admin").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/**").authenticated()
        ).formLogin(Customizer.withDefaults());

        // disable these two for the sake of h2
        // csrf is giving 403 while connecting to h2
        // headers are not allowing to open the database after connecting
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

    /**
     * This will provide a list of users who can access by logging in throw form
     * It will expect a list of UserDetails objects with username, password and roles.
     * Passwords must be encoded by any one of the password encoder.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails user1 = User.withUsername("dev").password(encoder().encode("dev")).roles("EMPLOYEE").build();
        UserDetails user2 = User.withUsername("test").password(encoder().encode("test")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user1, user2);
    }


    /**
     * Password encoder for encoding the passwords
     * I know it sounds stupid but that's what it will do
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}
