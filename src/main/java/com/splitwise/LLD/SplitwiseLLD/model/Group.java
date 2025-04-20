package com.splitwise.LLD.SplitwiseLLD.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Group {
    private String groupId;
    private String groupName;
    private List<User> users;
    private List<Expense> expenses;

    public Group(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.users = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }
}
