package com.bookshop.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.models.CartItem;
import com.bookshop.bookshop.repositories.BookRepository;
import com.bookshop.bookshop.services.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired private BookRepository bookRepository;
    @Autowired private CartService cartService;

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        cartService.addBook(book);
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long bookId) {
        cartService.removeBook(bookId);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(Model model) {
        for (CartItem item : cartService.getItems()) {
            Book book = item.getBook();
            int purchasedQty = item.getQuantity();

            if (book.getStock() >= purchasedQty) {
                book.setStock(book.getStock() - purchasedQty);
                bookRepository.save(book);
            } else {
                model.addAttribute("orderError", "Not enough stock for: " + book.getTitle());
                return "redirect:/cart?error=stock";
            }
        }

        cartService.clear();
        return "redirect:/?orderSuccess=true";
    }

}
