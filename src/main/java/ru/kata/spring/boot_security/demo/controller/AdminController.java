package ru.kata.spring.boot_security.demo.controller;


import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping
public class AdminController {
    private UserService service;
    @Autowired
    public AdminController (UserService service) {
        this.service = service;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model, Principal principal) {
        model.addAttribute("users", service.getUsers());
        model.addAttribute("admin", service.findByUsername(principal.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("rolesAdd", service.getRoles());
        return "admin";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user) {
        service.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute("user") User user) {
        service.editUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String removeUserById(Long id) {
        service.deleteUser(id);
        return "redirect:/admin";
    }

}
