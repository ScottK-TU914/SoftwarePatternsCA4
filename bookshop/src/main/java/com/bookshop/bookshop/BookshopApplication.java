package com.bookshop.bookshop;
import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.models.BookBuilder;
import com.bookshop.bookshop.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookshopApplication {
//Main application to be run
    public static void main(String[] args) {
        SpringApplication.run(BookshopApplication.class, args);
    }

    @Bean
    //to be ran after spring boots up, adding books into database using the builder pattern
    public CommandLineRunner addSampleBooks(BookRepository bookRepo) {
        return args -> {
            if (bookRepo.count() == 0) {
//saving via the book repository 
                bookRepo.save(new BookBuilder()
                        .setTitle("The Hobbit")
                        .setAuthor("J.R.R. Tolkien")
                        .setPublisher("HarperCollins")
                        .setCategory("Fantasy")
                        .setIsbn("9780007458424")
                        .setPrice(14.99)
                        .setImageFilename("hobbit.jpg")
                        .setStock(10)
                        .build());

                bookRepo.save(new BookBuilder()
                        .setTitle("Harry Potter and the Philosopher's Stone")
                        .setAuthor("J.K Rowling")
                        .setPublisher("Bloomsbury")
                        .setCategory("Fantasy")
                        .setIsbn("9780747532699")
                        .setPrice(19.50)
                        .setImageFilename("potter.jpg")
                        .setStock(7)
                        .build());

                bookRepo.save(new BookBuilder()
                        .setTitle("Murder on the Orient Express")
                        .setAuthor("Agatha Christie")
                        .setPublisher("William Morrow")
                        .setCategory("Crime")
                        .setIsbn("9780062073501")
                        .setPrice(19.99)
                        .setImageFilename("orient.jpg")
                        .setStock(23)
                        .build());

                bookRepo.save(new BookBuilder()
                        .setTitle("The Hunger Games")
                        .setAuthor("Suzanne Collins")
                        .setPublisher("Scholastic")
                        .setCategory("Fiction")
                        .setIsbn("9781407132075")
                        .setPrice(30.99)
                        .setImageFilename("hungergames.jpg")
                        .setStock(30)
                        .build());

                bookRepo.save(new BookBuilder()
                        .setTitle("1984")
                        .setAuthor("George Orwell")
                        .setPublisher("Penguin")
                        .setCategory("Fiction")
                        .setIsbn("9780141036144")
                        .setPrice(14.99)
                        .setImageFilename("1984.jpg")
                        .setStock(2)
                        .build());

                bookRepo.save(new BookBuilder()
                        .setTitle("Of Mice and Men")
                        .setAuthor("John Steinbeck")
                        .setPublisher("Penguin")
                        .setCategory("Fiction")
                        .setIsbn("9780141023571")
                        .setPrice(10.00)
                        .setImageFilename("ofmiceandmen.jpg")
                        .setStock(25)
                        .build());

                System.out.println("Books added: Using Builder Pattern");
            }
        };
    }
}
