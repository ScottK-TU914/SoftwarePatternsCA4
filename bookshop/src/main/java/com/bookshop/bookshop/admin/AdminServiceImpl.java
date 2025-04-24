package com.bookshop.bookshop.admin;

import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //setting class for spring
public class AdminServiceImpl implements AdminService {

    private final BookRepository bookRepository;
//injecting constrcutor for spring and the bookrepo
    public AdminServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void increaseStock(Long bookId, int quantity) {
    	//Extracting a book by it's id
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        //If that book exists it'll be extracted
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
        //Increases the stock count of the book by the number provided
            book.setStock(book.getStock() + quantity);
            //saves updated stock
            bookRepository.save(book);
        }
    }
}
