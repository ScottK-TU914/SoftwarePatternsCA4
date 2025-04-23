package com.bookshop.bookshop.repositories;

import com.bookshop.bookshop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title); 
    List<Book> findByCategoryContainingIgnoreCase(String category);  
    List<Book> findByAuthorIgnoreCase(String author);
    List<Book> findByPublisherIgnoreCase(String publisher);
}