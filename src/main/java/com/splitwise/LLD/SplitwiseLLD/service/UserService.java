package com.splitwise.LLD.SplitwiseLLD.service;

import com.splitwise.LLD.SplitwiseLLD.exceptions.NoSuchUserFoundException;
import com.splitwise.LLD.SplitwiseLLD.exceptions.UserAlreadyExistException;
import com.splitwise.LLD.SplitwiseLLD.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {

    public static final Map<String, User> users = new HashMap<>();


    public User registerUser(String username, String email, String phoneNumber) {
        String userId = UUID.randomUUID().toString();
        User user = new User(userId,username, email, phoneNumber);
        if (users.containsKey(userId)) {
            throw new UserAlreadyExistException(String.format("User with id %s already exists", userId));
        }
        users.put(userId, user);
        return user;
    }

    public User getUser(String id) {
        if (!users.containsKey(id)) {
            throw new NoSuchUserFoundException(String.format("User with id %s not found", id));
        }
        return users.get(id);
    }
}
