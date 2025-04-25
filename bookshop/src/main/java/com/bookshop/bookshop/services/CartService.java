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
	//list of items that'll be in the cart
    private List<CartItem> items = new ArrayList<>();
    //strategy pattern to apply discounts
    private DiscountContext discountContext = new DiscountContext(); 
//add book to the cart, if already exists increase quantity that's in the cart already
    public void addBook(Book book) {
        for (CartItem item : items) {
        	if (item.getBook().getId() == book.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(book, 1));
    }
//removes a book by it's id from the cart
    public void removeBook(Long bookId) {
    	items.removeIf(item -> item.getBook().getId() == bookId);
    }
//shows all books in the cart
    public List<CartItem> getItems() {
        return items;
    }
//total price of checkout without discount added
    public double getTotal() {
        return items.stream().mapToDouble(item -> item.getBook().getPrice() * item.getQuantity()).sum();
    }
//total price after discount applied
    public double getDiscountedTotal() {
        return discountContext.applyDiscount(getTotal());
    }
//discount strategy set
    public void applyDiscountStrategy(DiscountStrategy strategy) {
        discountContext.setStrategy(strategy);
    }
//clears cart
    public void clear() {
        items.clear();
        discountContext.setStrategy(null); 
}
}
