package com.bookshop.bookshop.controllers;

import com.bookshop.bookshop.models.Customer;
import com.bookshop.bookshop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        String username = principal.getName();
        Customer customer = customerRepository.findByUsername(username);

        model.addAttribute("customer", customer);
        return "profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("customer") Customer updatedCustomer, Principal principal) {
        String username = principal.getName();
        Customer existingCustomer = customerRepository.findByUsername(username);

        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setPaymentMethod(updatedCustomer.getPaymentMethod());

        customerRepository.save(existingCustomer);
        return "redirect:/profile?success";
    }
}
