package com.bookshop.bookshop.strategy;

public class DiscountContext {

    private DiscountStrategy strategy;
    
    
    // Setting the strategy and allows changing the discount strategy at runtime.
    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }
//applies the currently set discount strategy to the given total.
    public double applyDiscount(double total) {
        if (strategy == null) {
        	//if no discount return standard pricing
            return total;
        }
        return strategy.applyDiscount(total);
    }
}
