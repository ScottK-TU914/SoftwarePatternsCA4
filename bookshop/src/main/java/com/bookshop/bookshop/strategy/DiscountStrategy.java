package com.bookshop.bookshop.strategy;
//strategy interface
public interface DiscountStrategy {
	//this takes the order total and returns the discounted total
    double applyDiscount(double total);
}
