package com.krs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "krs_users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String role;

}
