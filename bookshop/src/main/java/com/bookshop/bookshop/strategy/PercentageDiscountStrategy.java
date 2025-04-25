package com.bookshop.bookshop.strategy;
//strategy implements the DiscountStrategy interface
public class PercentageDiscountStrategy implements DiscountStrategy {

    private double percentage;

    public PercentageDiscountStrategy(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double total) {
    	//applies the percentage discount to the total
        return total * (1 - percentage);
    }
}
