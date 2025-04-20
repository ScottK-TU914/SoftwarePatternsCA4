package com.bookshop.bookshop.controllers;

import com.bookshop.bookshop.admin.AdminService;
import com.bookshop.bookshop.models.Book;
import com.bookshop.bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    @Qualifier("adminServiceProxy")
    private AdminService adminService;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/admin")
    public String adminPage(Model model,
                            @RequestParam(name = "success", required = false) String success,
                            @RequestParam(name = "error", required = false) String error) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("success", success != null);
        model.addAttribute("error", error);
        return "admin";
    }

    @PostMapping("/admin/increaseStock")
    public String increaseStock(@RequestParam Long bookId, @RequestParam int quantity) {
        try {
            adminService.increaseStock(bookId, quantity);
            return "redirect:/admin?success=true";
        } catch (SecurityException e) {
            return "redirect:/admin?error=unauthorized";
        } catch (Exception e) {
            return "redirect:/admin?error=true";
        }
    }
}
