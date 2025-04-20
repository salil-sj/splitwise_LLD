package com.splitwise.LLD.SplitwiseLLD.service;

import com.splitwise.LLD.SplitwiseLLD.exceptions.GroupAlreadyExistsException;
import com.splitwise.LLD.SplitwiseLLD.exceptions.NoSuchGroupFoundException;
import com.splitwise.LLD.SplitwiseLLD.model.Group;
import com.splitwise.LLD.SplitwiseLLD.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GroupService {

    private static final Map<String, Group> groups = new HashMap<>();


    public Group createGroup(String name, List<User> users) {
        String id = UUID.randomUUID().toString();
        Group group = new Group(id, name);

        for (User user : users) {
            group.addUser(user);
        }

        if (groups.containsKey(id)) {
            throw new GroupAlreadyExistsException(String.format("Group with id %s already exists", id));
        }
        groups.put(id, group);
        return group;
    }

    public Group getGroup(String id) {
        if (!groups.containsKey(id)) {
            throw new NoSuchGroupFoundException(String.format("User with id %s does not exist", id));
        }
        return groups.get(id);
    }
}
