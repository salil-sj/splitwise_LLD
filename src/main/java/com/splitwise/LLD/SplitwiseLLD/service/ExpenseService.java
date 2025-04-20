package com.splitwise.LLD.SplitwiseLLD.service;

import com.splitwise.LLD.SplitwiseLLD.model.*;
import com.splitwise.LLD.SplitwiseLLD.strategy.expenseStrategy.ExpenseSettleStrategy;
import com.splitwise.LLD.SplitwiseLLD.strategy.expenseStrategy.SettleStrategyType;
import com.splitwise.LLD.SplitwiseLLD.strategy.splitStrategy.SplitStrategy;
import com.splitwise.LLD.SplitwiseLLD.strategy.splitStrategy.SplitStrategyType;

import java.util.*;

public class ExpenseService {

    public static final Map<String, Expense> expenseMap = new HashMap<>();

    private SplitStrategy splitStrategy;
    private GroupService groupService;
    ExpenseSettleStrategy expenseSettleStrategy;

    public ExpenseService(GroupService groupService) {
        this.groupService = groupService;
    }

    public Expense createExpense(Double amount, User paidBy, String description, Date date, String groupId, String splitStrategyType, Map<String, Object> splitMetadata) {

        String id = UUID.randomUUID().toString();
        ExpenseMetaData expenseMetaData = new ExpenseMetaData(description, date, groupId);

        //Group
        Group group = groupService.getGroup(groupId);
        List<User> usersInGroup = group.getUsers();
        //Dynamically using
        splitStrategy = SplitStrategyType.valueOf(splitStrategyType.toUpperCase()).getStrategy();
        List<Split> splits = splitStrategy.getSplits(usersInGroup, amount, splitMetadata);

        Expense expense = new Expense(id, amount, paidBy, splits, expenseMetaData);
        if (!expense.validateExpense()) {
            throw new IllegalArgumentException();
        }
        group.addExpense(expense);
        expenseMap.put(id, expense);

        return expense;
    }

    public List<Transcation> settleExpense(String groupId, String settleStrategyType) {
        expenseSettleStrategy = SettleStrategyType.valueOf(settleStrategyType.toUpperCase()).getExpenseSettleStrategy();
        List<Expense> expenses = groupService.getGroup(groupId).getExpenses();
        return expenseSettleStrategy.settleExpense(expenses);
    }

    public List<Expense> getExpenseForUser(String userId) {
        List<Expense> userExpenses = new ArrayList<>();

        for (Expense expense : expenseMap.values()) {
            if (expense.getPaidBy().getId().equals(userId)) {
                userExpenses.add(expense);
                continue;
            }
            for (Split split : expense.getSplits()) {
                if (split.getUser().getId().equals(userId)) {
                    userExpenses.add(expense);
                    break;
                }
            }
        }
        return userExpenses;

    }

    public List<Expense> getExpenseForGroup(String groupId) {
        Group group = groupService.getGroup(groupId);
        return group.getExpenses();
    }
}
