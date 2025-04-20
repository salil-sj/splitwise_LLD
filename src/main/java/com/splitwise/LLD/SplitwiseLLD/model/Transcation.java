package com.splitwise.LLD.SplitwiseLLD.model;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class Transcation {

    private User from;
    private User to;
    private Double amount;


    @Override
    public String toString() {
        return from.getName() + "-owes" + amount + ": to " + to.getName();
    }
}
