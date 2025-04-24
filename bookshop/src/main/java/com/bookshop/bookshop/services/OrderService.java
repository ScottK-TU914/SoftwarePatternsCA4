package com.bookshop.bookshop.services;

import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.models.Customer;
import com.bookshop.bookshop.models.Order;
import com.bookshop.bookshop.repositories.BookRepository;
import com.bookshop.bookshop.repositories.CustomerRepository;
import com.bookshop.bookshop.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private OrderRepository orderRepository;

    public void placeOrder(Long bookId, String username, int quantity) {
        Book book = bookRepo.findById(bookId).orElse(null);
        Customer customer = customerRepo.findByUsername(username);

        if (book != null && customer != null && book.getStock() >= quantity) {
            book.setStock(book.getStock() - quantity);
            bookRepo.save(book);

            Order order = new Order(
                customer,
                book,
                quantity,
                book.getPrice() * quantity,
                LocalDateTime.now()
            );
            orderRepository.save(order); 

            System.out.println("Order placed: " + quantity + "x " + book.getTitle() + " for " + username);
        } else {
            throw new RuntimeException("Invalid order: book or customer not found, or insufficient stock.");
        }
    }



    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
