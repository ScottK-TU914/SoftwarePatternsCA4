package com.bookshop.bookshop.controllers;

import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.models.Customer;
import com.bookshop.bookshop.models.Review;
import com.bookshop.bookshop.repositories.BookRepository;
import com.bookshop.bookshop.repositories.CustomerRepository;
import com.bookshop.bookshop.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired private BookRepository bookRepo;
    @Autowired private ReviewRepository reviewRepo;
    @Autowired private CustomerRepository customerRepo;

    @PostMapping("/add")
    public String addReview(@RequestParam Long bookId,
                            @RequestParam int rating,
                            @RequestParam String comment,
                            @AuthenticationPrincipal User user) {

        Customer customer = customerRepo.findByUsername(user.getUsername());
        Book book = bookRepo.findById(bookId).orElse(null);

        if (book != null && customer != null) {
            Review review = new Review(customer, book, rating, comment, LocalDateTime.now());
            reviewRepo.save(review);
        }

        return "redirect:/?reviewSuccess=true";

    }

    @GetMapping("/book/{id}")
    public String showReviews(@PathVariable Long id, Model model) {
        Book book = bookRepo.findById(id).orElse(null);
        model.addAttribute("book", book);
        model.addAttribute("reviews", reviewRepo.findByBook(book));
        return "review";
    }
}
