package com.krs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringSecurityBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityBasicApplication.class, args);
    }

    @GetMapping("/")
    public String getMessage() {
        return "Anyone can access this method..!";
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