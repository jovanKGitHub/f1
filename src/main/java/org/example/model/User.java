package org.example.model;

import java.math.BigDecimal;
import java.util.UUID;

public class User {
    private UUID id;
    private BigDecimal balance;

    public User(UUID id) {
        this.id = id;
        this.balance = new BigDecimal("100.00"); // Initial balance
    }

    public UUID getId() { return id; }
    public BigDecimal getBalance() { return balance; }

    public void deductBalance(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance = balance.subtract(amount);
    }

    public void addBalance(BigDecimal amount) {
        balance = balance.add(amount);
    }
}