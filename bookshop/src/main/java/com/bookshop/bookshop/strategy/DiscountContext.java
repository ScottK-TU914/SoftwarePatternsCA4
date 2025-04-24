package com.bookshop.bookshop.strategy;

public class DiscountContext {

    private DiscountStrategy strategy;

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double applyDiscount(double total) {
        if (strategy == null) {
            return total;
        }
        return strategy.applyDiscount(total);
    }
}
