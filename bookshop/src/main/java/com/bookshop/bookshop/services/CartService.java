package com.bookshop.bookshop.services;

import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.models.CartItem;
import com.bookshop.bookshop.strategy.DiscountContext;
import com.bookshop.bookshop.strategy.DiscountStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private List<CartItem> items = new ArrayList<>();
    private DiscountContext discountContext = new DiscountContext(); 

    public void addBook(Book book) {
        for (CartItem item : items) {
        	if (item.getBook().getId() == book.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(book, 1));
    }

    public void removeBook(Long bookId) {
    	items.removeIf(item -> item.getBook().getId() == bookId);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        return items.stream().mapToDouble(item -> item.getBook().getPrice() * item.getQuantity()).sum();
    }

    public double getDiscountedTotal() {
        return discountContext.applyDiscount(getTotal());
    }

    public void applyDiscountStrategy(DiscountStrategy strategy) {
        discountContext.setStrategy(strategy);
    }

    public void clear() {
        items.clear();
        discountContext.setStrategy(null); 
}
}
