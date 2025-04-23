package com.ijse.orm.mentalhealthcenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User implements SuperEntity{
    @Id
    private String username;
    private String password;
    private String role;
}
