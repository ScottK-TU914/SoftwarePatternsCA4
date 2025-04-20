package com.bookshop.bookshop.admin;

import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final BookRepository bookRepository;

    public AdminServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void increaseStock(Long bookId, int quantity) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            book.setStock(book.getStock() + quantity);
            bookRepository.save(book);
        }
    }
}
