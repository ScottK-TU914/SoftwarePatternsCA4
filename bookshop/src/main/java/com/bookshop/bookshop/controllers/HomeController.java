package com.bookshop.bookshop.controllers;


import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.repositories.BookRepository;
import com.bookshop.bookshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final BookRepository bookRepository;

//constructor for book repo and order service
    @Autowired
    public HomeController(BookRepository bookRepository, OrderService orderService) {
        this.bookRepository = bookRepository;
    }
//shows the homepage with search, filter and sorting
    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(name = "orderSuccess", required = false) String orderSuccess,
                       @RequestParam(name = "search", required = false) String search,
                       @RequestParam(name = "filterBy", required = false) String filterBy,
                       @RequestParam(name = "sortBy", required = false) String sortBy,
                       @RequestParam(name = "order", required = false) String order) {
//filtering the book list if search and filterby are used
        List<Book> books;
        if (search != null && !search.isEmpty() && filterBy != null) { 
            switch (filterBy) {
                case "title":
                    books = bookRepository.findByTitleContainingIgnoreCase(search);
                    break;
                case "category":
                    books = bookRepository.findByCategoryContainingIgnoreCase(search);
                    break;
                case "author":
                    books = bookRepository.findByAuthorIgnoreCase(search);
                    break;
                case "publisher":
                    books = bookRepository.findByPublisherIgnoreCase(search);
                    break;
                default:
                    books = bookRepository.findAll();
            }
        } else {
            books = bookRepository.findAll();
        }

        // Applying sorting if sort by and order are provided
        if (sortBy != null && order != null) {
            Comparator<Book> comparator = switch (sortBy) {
                case "title" -> Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER);
                case "author" -> Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER);
                case "publisher" -> Comparator.comparing(Book::getPublisher, String.CASE_INSENSITIVE_ORDER);
                case "price" -> Comparator.comparing(Book::getPrice);
                default -> null;
            };

            if (comparator != null) {
                if ("desc".equals(order)) {
                    comparator = comparator.reversed();
                }
                books = books.stream().sorted(comparator).collect(Collectors.toList());
            }
        }

        model.addAttribute("books", books);
        model.addAttribute("orderSuccess", orderSuccess != null);
        return "home";
    }
}
