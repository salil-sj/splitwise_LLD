package com.splitwise.LLD.SplitwiseLLD.strategy.expenseStrategy;

import com.splitwise.LLD.SplitwiseLLD.model.Expense;
import com.splitwise.LLD.SplitwiseLLD.model.Transcation;

import java.util.List;

public interface ExpenseSettleStrategy {

    List<Transcation> settleExpense(List<Expense> expenses);
}
