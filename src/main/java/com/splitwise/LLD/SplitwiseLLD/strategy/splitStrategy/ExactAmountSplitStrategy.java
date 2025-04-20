package com.splitwise.LLD.SplitwiseLLD.strategy.splitStrategy;

import com.splitwise.LLD.SplitwiseLLD.model.Split;
import com.splitwise.LLD.SplitwiseLLD.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExactAmountSplitStrategy implements SplitStrategy{

    @Override
    public List<Split> getSplits(List<User> users, double amount, Map<String, Object> metadata) {

        List<Split> splits = new ArrayList<Split>();
        @SuppressWarnings("unchecked")
        Map<String,Double> amounts =   (Map<String,Double>)metadata.get("exactAmounts");

        for(User user : users){
            Double userAmount = amounts.getOrDefault(user.getId(),0.0);
            splits.add(new Split(user, userAmount));
        }
        return splits;
    }
}
