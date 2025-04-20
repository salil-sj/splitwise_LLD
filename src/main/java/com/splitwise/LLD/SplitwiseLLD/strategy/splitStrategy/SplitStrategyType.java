package com.splitwise.LLD.SplitwiseLLD.strategy.splitStrategy;

public enum SplitStrategyType {

    EQUAL(new EqualSplitStrategy()),
    PERCENTAGE(new PercentageSplitStrategy()),
    EXACT(new ExactAmountSplitStrategy());

    private final SplitStrategy strategy;

    SplitStrategyType(SplitStrategy strategy) {
        this.strategy = strategy;
    }

    public SplitStrategy getStrategy() {
        return strategy;
    }
}
