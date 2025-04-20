package com.bookshop.bookshop.models;

public class BookBuilder {
    private String title;
    private String author;
    private String publisher;
    private String category;
    private String isbn;
    private double price;
    private String imageFilename;
    private int stock;

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public BookBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public BookBuilder setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public BookBuilder setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
        return this;
    }

    public BookBuilder setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public Book build() {
        return new Book(title, author, publisher, category, isbn, price, imageFilename, stock);
    }
}
