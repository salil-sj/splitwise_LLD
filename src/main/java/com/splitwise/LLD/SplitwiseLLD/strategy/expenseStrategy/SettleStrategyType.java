package com.splitwise.LLD.SplitwiseLLD.strategy.expenseStrategy;

public enum SettleStrategyType {

    DEFAULT(new DefaultExpenseSettle());

    private final ExpenseSettleStrategy expenseSettleStrategy;

    SettleStrategyType(ExpenseSettleStrategy expenseSettleStrategy) {
        this.expenseSettleStrategy = expenseSettleStrategy;
    }

    public ExpenseSettleStrategy getExpenseSettleStrategy() {
        return expenseSettleStrategy;
    }
}
