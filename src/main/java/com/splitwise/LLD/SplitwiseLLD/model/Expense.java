package com.splitwise.LLD.SplitwiseLLD.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Expense {
    private String id;
    private Double amount;
    private User paidBy;
    private List<Split> splits;
    private ExpenseMetaData expenseMetaData;

    //validate expense:
    public boolean validateExpense() {
        double totalAmount = splits.stream().mapToDouble(split->split.getAmount()).sum();
        return Math.abs(totalAmount - amount) < 0.0001;   // room for tiny rounding off errors
    }

}
