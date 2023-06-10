package com.krs;

import com.krs.entity.User;
import com.krs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringSecurityDatabaseApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDatabaseApplication.class, args);
    }

    @PostMapping("/reg")
    public User registerUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping("/")
    public String getMessage() {
        return "Anyone can access this method..!";
    }

    @GetMapping("/principal")
    public User getPrincipal(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping("/super-admin")
    public String getSuperAdminMessage() {
        return "Only super admin can access this..!";
    }

    @GetMapping("/admin")
    public String getAdminMessage() {
        return "Only admin can see this message..!";
    }

    @GetMapping("/employee")
    public String getEmployeeMessage() {
        return "Only employee can see this message..!";
    }

    @GetMapping("/emp-admin")
    public String getMultiAccessMessage() {
        return "Admin and employee can only access this..!";
    }

}
