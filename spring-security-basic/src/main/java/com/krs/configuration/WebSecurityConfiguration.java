package com.krs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

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

/*

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated());
*/
        return http.build();
}


}
