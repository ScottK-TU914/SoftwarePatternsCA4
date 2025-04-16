package com.bookshop.bookshop.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bookshop.bookshop.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {	
}


