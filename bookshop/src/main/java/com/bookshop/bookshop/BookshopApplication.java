package com.bookshop.bookshop;

import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookshopApplication.class, args);
    }

    @Bean
    public CommandLineRunner addSampleBook(BookRepository bookRepo) {
        return args -> {
            if (bookRepo.count() == 0) {
                bookRepo.save(new Book(
                        "The Hobbit",
                        "J.R.R. Tolkien",
                        "HarperCollins",
                        "Fantasy",
                        "9780007458424",
                        14.99,
                        "hobbit.jpg",
                        10
                ));
                System.out.println("Book added: The Hobbit");
            }
        };
    }
}
