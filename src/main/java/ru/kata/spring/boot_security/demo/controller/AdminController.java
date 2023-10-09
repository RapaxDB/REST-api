package ru.kata.spring.boot_security.demo.controller;


import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping
public class AdminController {
    private UserService service;
    @Autowired
    public AdminController (UserService service) {
        this.service = service;
    }

    @GetMapping("/admin/users")
    public String printUsers(Model model) {
        model.addAttribute("list", service.getUsers());
        return "/users";
    }

    @GetMapping("/admin/edit")
    public String editUser(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "edit";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/admin/users")
    public String createUser(@ModelAttribute("user") User user) {
        service.addUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam(value = "id") Long id) {
        service.deleteUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        service.editUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user")
    public String printUser(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "user";
    }
}
