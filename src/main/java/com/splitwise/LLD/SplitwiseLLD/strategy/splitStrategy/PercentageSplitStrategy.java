package com.splitwise.LLD.SplitwiseLLD.strategy.splitStrategy;

import com.splitwise.LLD.SplitwiseLLD.model.Split;
import com.splitwise.LLD.SplitwiseLLD.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> getSplits(List<User> users, double amount, Map<String, Object> metadata) {

        List<Split> splits = new ArrayList<Split>();
        @SuppressWarnings("unchecked")
        Map<String, Double> percentages = (Map<String, Double>)metadata.get("percentages");

        for(User user : users) {
            Double percentage = percentages.getOrDefault(user.getId(), 0.0);
            double newAmount = amount * percentage/100;
            splits.add(new Split(user, newAmount));
        }
        return splits;
    }
}
