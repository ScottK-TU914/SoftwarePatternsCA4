package com.bookshop.bookshop.repositories;

import com.bookshop.bookshop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//Used for filtering / searches
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);  //looks for a book by title
    List<Book> findByCategoryContainingIgnoreCase(String category);   //looks for a book by category
    List<Book> findByAuthorIgnoreCase(String author); //looks for a book by author
    List<Book> findByPublisherIgnoreCase(String publisher); //looks for a book by publisher
}