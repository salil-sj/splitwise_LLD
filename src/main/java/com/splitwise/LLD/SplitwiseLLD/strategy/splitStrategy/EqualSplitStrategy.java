package com.splitwise.LLD.SplitwiseLLD.strategy.splitStrategy;

import com.splitwise.LLD.SplitwiseLLD.model.Split;
import com.splitwise.LLD.SplitwiseLLD.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> getSplits(List<User> users, double amount, Map<String, Object> metadata) {

        List<Split> splits = new ArrayList<Split>();
        double eachPersonAmount= amount/ users.size();
        for (User user : users) {
            Split split = new Split(user, eachPersonAmount);
            splits.add(split);
        }
        return splits;
    }
}
