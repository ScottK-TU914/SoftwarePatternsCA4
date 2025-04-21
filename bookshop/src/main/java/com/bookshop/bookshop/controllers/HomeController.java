package com.bookshop.bookshop.controllers;

import com.bookshop.bookshop.command.OrderInvoker;
import com.bookshop.bookshop.command.PlaceOrderCommand;
import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.repositories.BookRepository;
import com.bookshop.bookshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private final BookRepository bookRepository;
    private final OrderService orderService;

    @Autowired
    public HomeController(BookRepository bookRepository, OrderService orderService) {
        this.bookRepository = bookRepository;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(name = "orderSuccess", required = false) String orderSuccess) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("orderSuccess", orderSuccess != null);
        return "home";
    }

    @PostMapping("/buy")
    public String placeOrder(@RequestParam("bookId") Long bookId,
                             @AuthenticationPrincipal User user) {
        PlaceOrderCommand placeOrder = new PlaceOrderCommand(orderService, bookId, user.getUsername());
        OrderInvoker invoker = new OrderInvoker();
        invoker.setCommand(placeOrder);
        invoker.placeOrder();

        return "redirect:/?orderSuccess=true";
    }
}
