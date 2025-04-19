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

                bookRepo.save(new Book(
                        "Harry Potter and the Philosopher's Stone",
                        "J.K Rowling",
                        "Bloomsbury",
                        "Fantasy",
                        "9780747532699",
                        19.50,
                        "potter.jpg",
                        7
                ));

                bookRepo.save(new Book(
                        "Murder on the Orient Express",
                        "Agatha Christie",
                        "William Morrow",
                        "Crime",
                        "9780062073501",
                        19.99,
                        "orient.jpg",
                        23
                ));
                bookRepo.save(new Book(
                        "The Hunger Games",
                        "Suzanne Collins",
                        "Scholastic",
                        "Fiction",
                        "9781407132075",
                        30.99,
                        "hungergames.jpg",
                        30
                ));
                bookRepo.save(new Book(
                        "1984",
                        "George Orwell",
                        "Penguin",
                        "Fiction",
                        "9780141036144",
                        14.99,
                        "1984.jpg",
                        2
                ));
                bookRepo.save(new Book(
                        "Of Mice and Men",
                        "John Steinbeck",
                        "Penguin",
                        "Fiction",
                        "9780141023571",
                        10.00,
                        "ofmiceandmen.jpg",
                        25
                ));

                System.out.println("Books added: The Hobbit, Harry Potter and the Philosopher's Stone, Murder on the Orient Express, The Hunger Games, 1984, Of Mice and Men  ");
            }
        };
    }
}
