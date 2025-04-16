package com.bookshop.bookshop.controllers;

import com.bookshop.bookshop.models.Customer;
import com.bookshop.bookshop.repositories.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final CustomerRepository customerRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(CustomerRepository customerRepo, BCryptPasswordEncoder encoder) {
        this.customerRepo = customerRepo;
        this.passwordEncoder = encoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String username,
                                      @RequestParam String email,
                                      @RequestParam String password) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setPassword(passwordEncoder.encode(password));
        customerRepo.save(customer);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
