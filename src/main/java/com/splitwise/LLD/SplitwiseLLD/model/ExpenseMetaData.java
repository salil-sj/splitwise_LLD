package com.splitwise.LLD.SplitwiseLLD.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ExpenseMetaData {

    private String description;
    private Date date;
    private String groupiId;
}
