package com.bookshop.bookshop.controllers;

import com.bookshop.bookshop.command.CartOrderCommand;
import com.bookshop.bookshop.command.CommandService;
import com.bookshop.bookshop.command.OrderInvoker;
import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.models.CartItem;
import com.bookshop.bookshop.repositories.BookRepository;
import com.bookshop.bookshop.services.CartService;
import com.bookshop.bookshop.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired private BookRepository bookRepository;
    @Autowired private CartService cartService;
    @Autowired private OrderService orderService;
    @Autowired private OrderInvoker orderInvoker;

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
    public String checkout(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        for (CartItem item : cartService.getItems()) {
        	CartOrderCommand command = new CartOrderCommand(
        		    orderService,
        		    Long.valueOf(item.getBook().getId()),
        		    user.getUsername(),
        		    item.getQuantity()
        		);
            orderInvoker.executeCommand(command);
        }

        cartService.clear();
        return "redirect:/?orderSuccess=true";
    }
}

