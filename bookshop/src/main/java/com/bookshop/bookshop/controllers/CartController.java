package com.bookshop.bookshop.controllers;

import com.bookshop.bookshop.command.CartOrderCommand;
import com.bookshop.bookshop.command.CommandService;
import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.models.CartItem;
import com.bookshop.bookshop.repositories.BookRepository;
import com.bookshop.bookshop.services.CartService;
import com.bookshop.bookshop.services.OrderService;
import com.bookshop.bookshop.strategy.PercentageDiscountStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired private BookRepository bookRepository;
    @Autowired private CartService cartService;
    @Autowired private OrderService orderService;
    @Autowired private CommandService commandService;
//shows the cart page with current cart items, cost and discount
    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        model.addAttribute("discountedTotal", cartService.getDiscountedTotal());
        return "cart";
    }

//adds a selected book to the cart, and shows alert
    @PostMapping("/add")
    public String addToCart(@RequestParam Long bookId, RedirectAttributes redirectAttributes) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        cartService.addBook(book);
        redirectAttributes.addFlashAttribute("addedToCart", true);
        return "redirect:/";
    }

//removes a book from the cart
    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long bookId) {
        cartService.removeBook(bookId);
        return "redirect:/cart";
    }
//goes to checkout with the book and passes data such as username
    @PostMapping("/checkout")
    public String checkout(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        for (CartItem item : cartService.getItems()) {
        	CartOrderCommand cartOrderCommand = new CartOrderCommand(
        		    orderService,
        		    Long.valueOf(item.getBook().getId()),
        		    user.getUsername(),
        		    item.getQuantity()
        		);
        	commandService.execute(cartOrderCommand);

        }

        cartService.clear();
        return "redirect:/?orderSuccess=true";
    }
   //applies a discount code to the cart using the strategy pattern 
    @PostMapping("/applyVoucher")
    public String applyVoucher(@RequestParam String voucherCode, RedirectAttributes redirectAttributes) {
        if (voucherCode.equalsIgnoreCase("APRIL20")) {
            cartService.applyDiscountStrategy(new PercentageDiscountStrategy(0.20));
            redirectAttributes.addFlashAttribute("voucherApplied", true);
        } else {
            redirectAttributes.addFlashAttribute("invalidVoucher", true);
        }
        return "redirect:/cart";
    }


    
}

