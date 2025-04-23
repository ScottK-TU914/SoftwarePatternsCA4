package com.bookshop.bookshop.services;

import java.util.ArrayList;
import java.util.List;

import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.models.CartItem;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private List<CartItem> items = new ArrayList<>();

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

    public void clear() {
        items.clear();
    }
}
