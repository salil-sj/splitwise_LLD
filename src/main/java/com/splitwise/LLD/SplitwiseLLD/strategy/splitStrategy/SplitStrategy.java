package com.splitwise.LLD.SplitwiseLLD.strategy.splitStrategy;

import com.splitwise.LLD.SplitwiseLLD.model.Split;
import com.splitwise.LLD.SplitwiseLLD.model.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {


    public List<Split> getSplits(List<User> users, double amount , Map<String, Object> metadata);
}

  /*
    - The metadata map is required to flexibly pass
    strategy-specific data to different implementations of SplitStrategy without
    changing the method signature.

    -  like with percntage split, we would pass "percentage" , Map of  userId, percentage
     */
