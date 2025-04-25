package com.bookshop.bookshop.repositories;

import com.bookshop.bookshop.models.Review;
import com.bookshop.bookshop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	//get all reviews on specific book
    List<Review> findByBook(Book book);
}
