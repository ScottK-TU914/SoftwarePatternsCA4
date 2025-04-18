package com.bookshop.bookshop.models;

import jakarta.persistence.*;
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	private String title;
    private String author;
    private String isbn;
    private Double price;
    private String publisher;
    private String category;
    private String imageFilename;
    private int stock;

    public Book() {
    }

    public Book(String title, String author, String publisher, String category, String isbn,
                double price, String imageFilename, int stock) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setPublisher(publisher);
        this.setCategory(category);
        this.setIsbn(isbn);
        this.setPrice(price);
        this.setImageFilename(imageFilename);
        this.setStock(stock);
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}

