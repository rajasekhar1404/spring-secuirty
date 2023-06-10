package com.krs.service;

import com.krs.entity.User;
import com.krs.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserAuthenticationProvider(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userRepository.findByUsername(username);

        if (user != null) {
            if (user.getUsername().equals(username) && encoder.matches(password, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(user, "", getUserRoles(user.getRoles()));
            } else {
                throw new RuntimeException("You are not authorized");
            }
        } else {
            throw new RuntimeException("You are not authorized");
        }

    }

    List<GrantedAuthority> getUserRoles(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
