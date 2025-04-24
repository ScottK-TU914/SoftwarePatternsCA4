package com.bookshop.bookshop.strategy;

public class PercentageDiscountStrategy implements DiscountStrategy {

    private double percentage;

    public PercentageDiscountStrategy(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double total) {
        return total * (1 - percentage);
    }
}
