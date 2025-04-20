package com.splitwise.LLD.SplitwiseLLD.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private String id;
    private String name;
    private String email;
    private String phone;

    public User(String id,String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
