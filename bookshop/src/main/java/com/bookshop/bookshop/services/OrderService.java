package com.bookshop.bookshop.services;

import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.models.Customer;
import com.bookshop.bookshop.repositories.BookRepository;
import com.bookshop.bookshop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private CustomerRepository customerRepo;

    public void placeOrder(Long bookId, String username, int quantity) {
        Book book = bookRepo.findById(bookId).orElse(null);
        Customer customer = customerRepo.findByUsername(username);

        if (book != null && customer != null && book.getStock() >= quantity) {
            book.setStock(book.getStock() - quantity);
            bookRepo.save(book);
            System.out.println("Order placed: " + quantity + "x " + book.getTitle() + " for " + username);
        } else {
            throw new RuntimeException("Invalid order: book or customer not found, or insufficient stock.");
        }
    }
}
