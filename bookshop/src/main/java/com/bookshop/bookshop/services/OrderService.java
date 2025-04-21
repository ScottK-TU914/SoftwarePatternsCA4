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

    public void placeOrder(Long bookId, String username) {
        Book book = bookRepo.findById(bookId).orElse(null);
        Customer customer = customerRepo.findByUsername(username);

        if (book != null && book.getStock() > 0) {
            book.setStock(book.getStock() - 1);
            bookRepo.save(book);
            System.out.println("Order placed for " + book.getTitle() + " by " + username);
        } else {
            throw new RuntimeException("Book out of stock");
        }
    }
}
