package com.bookshop.bookshop.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Book book;

    private int rating;
    private String comment;
    private LocalDateTime date;

    public Review() {}

    public Review(Customer customer, Book book, int rating, String comment, LocalDateTime date) {
        this.customer = customer;
        this.book = book;
        this.setRating(rating);
        this.setComment(comment);
        this.setDate(date);
    }

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
