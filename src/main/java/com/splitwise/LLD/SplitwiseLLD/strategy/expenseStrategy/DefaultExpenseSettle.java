package com.splitwise.LLD.SplitwiseLLD.strategy.expenseStrategy;

import com.splitwise.LLD.SplitwiseLLD.model.Expense;
import com.splitwise.LLD.SplitwiseLLD.model.Split;
import com.splitwise.LLD.SplitwiseLLD.model.Transcation;
import com.splitwise.LLD.SplitwiseLLD.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultExpenseSettle implements ExpenseSettleStrategy {

    @Override
    public List<Transcation> settleExpense(List<Expense> expenses) {
        Map<String, Double> balanceSheet = new HashMap<>();

        // Calculate net balance for each user
        for (Expense expense : expenses) {
            User paidBy = expense.getPaidBy();
            double amount = expense.getAmount();

            // Credit the payer
            String payerId = paidBy.getId();
            balanceSheet.put(payerId, balanceSheet.getOrDefault(payerId, 0.0) + amount);

            // Debit the split users
            for (Split split : expense.getSplits()) {
                User user = split.getUser();
                String userId = user.getId();
                balanceSheet.put(userId, balanceSheet.getOrDefault(userId, 0.0) - split.getAmount());
            }
        }

        List<Transcation> transactions = new ArrayList<>();

        // Separate users with positive and negative balances
        List<Map.Entry<String, Double>> positiveBalances = new ArrayList<>();
        List<Map.Entry<String, Double>> negativeBalances = new ArrayList<>();

        Map<String, User> userMap = new HashMap<>();
        for (Expense expense : expenses) {
            userMap.put(expense.getPaidBy().getId(), expense.getPaidBy());
            for (Split split : expense.getSplits()) {
                userMap.put(split.getUser().getId(), split.getUser());
            }
        }

        for (Map.Entry<String, Double> entry : balanceSheet.entrySet()) {
            if (Math.abs(entry.getValue()) < 0.001) continue; // Skip settled users

            if (entry.getValue() > 0) {
                positiveBalances.add(entry);
            } else {
                negativeBalances.add(entry);
            }
        }

        // Sort balances
        positiveBalances.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        negativeBalances.sort((a, b) -> Double.compare(a.getValue(), b.getValue()));

        // Create transactions
        int i = 0, j = 0;
        while (i < positiveBalances.size() && j < negativeBalances.size()) {
            String creditorId = positiveBalances.get(i).getKey();
            String debtorId = negativeBalances.get(j).getKey();

            double creditorAmount = positiveBalances.get(i).getValue();
            double debtorAmount = negativeBalances.get(j).getValue();

            double transactionAmount = Math.min(creditorAmount, Math.abs(debtorAmount));

            // Create transaction
            transactions.add(new Transcation(
                    userMap.get(debtorId),
                    userMap.get(creditorId),
                    transactionAmount
            ));

            // Update balances
            double creditorRemaining = creditorAmount - transactionAmount;
            double debtorRemaining = debtorAmount + transactionAmount;

            positiveBalances.get(i).setValue(creditorRemaining);
            negativeBalances.get(j).setValue(debtorRemaining);

            if (Math.abs(creditorRemaining) < 0.001) i++;
            if (Math.abs(debtorRemaining) < 0.001) j++;
        }

        return transactions;

    }
}
