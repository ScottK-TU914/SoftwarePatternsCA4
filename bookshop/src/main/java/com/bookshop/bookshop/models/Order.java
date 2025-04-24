package com.bookshop.bookshop.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Book book;

    private int quantity;

    private double totalPrice;

    private LocalDateTime orderDate;


    public Order() {}

    public Order(Customer customer, Book book, int quantity, double totalPrice, LocalDateTime orderDate) {
        this.customer = customer;
        this.book = book;
        this.setQuantity(quantity);
        this.setTotalPrice(totalPrice);
        this.setOrderDate(orderDate);
    }

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
    
	public Customer getCustomer() {
	    return customer;
	}

	public void setCustomer(Customer customer) {
	    this.customer = customer;
	}

	public Book getBook() {
	    return book;
	}

	public void setBook(Book book) {
	    this.book = book;
	}

}