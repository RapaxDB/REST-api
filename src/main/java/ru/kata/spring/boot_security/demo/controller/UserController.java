package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping
public class UserController {

    private UserService service;
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    // Доступ для user
//    @GetMapping("/user")
//    public String printUser(@RequestParam(value = "id") Long id, Model model) {
//        model.addAttribute("user", service.getUserById(id));
//        return "user";
//    }
    @GetMapping("/user")
    public String profileUser(Model model, Principal principal) {
        model.addAttribute("user", service.findByUsername(principal.getName()));
        return "user";
    }
}

//@GetMapping("/user")
//    public String printUser(@RequestParam(value = "id") Long id, Model model) {
//        model.addAttribute("user", service.getUserById(id));
//        return "user";
//    }